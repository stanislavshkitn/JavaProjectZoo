package controllers;

import jdbc.AnimalsJDBC;
import jdbc.AviaryJDBC;
import jdbc.LocationJDBC;
import models.Animal;
import models.Aviary;
import models.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewLocationController {
    @GetMapping("/new-location")
    public String getAnimals(Model model){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AnimalsJDBC jdbcTemplateAnimals =
                (AnimalsJDBC) context.getBean("jdbcTemplateAnimals");

        model.addAttribute("animals", jdbcTemplateAnimals.getAll());
        return "new_location";
    }

    @PostMapping("/delete-animal/{pk}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("pk") String pk) {

        if (pk == null || pk.equals("")) {
            return Ajax.emptyResponse();
        }
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AnimalsJDBC jdbcTemplateAnimals =
                (AnimalsJDBC) context.getBean("jdbcTemplateAnimals");
        jdbcTemplateAnimals.delete(pk);
        return Ajax.emptyResponse();
    }

    @PostMapping("/add-animal/")
    public @ResponseBody
    Map<String, Object> add(@RequestParam("name") String name, @RequestParam("is_predator") Boolean is_predator){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AnimalsJDBC jdbcTemplateAnimals =
                (AnimalsJDBC) context.getBean("jdbcTemplateAnimals");
        Animal animal = new Animal(name, is_predator);
        try {
            jdbcTemplateAnimals.save(animal);

        }catch (Exception e){
            String message = "Такое животное уже есть, зачем тебе ещё?";
            return Ajax.errorResponse(message);
        }
        return Ajax.successResponse(animal);
    }

    @PostMapping("/save-location/")
    public @ResponseBody
    Map<String, Object> save(@RequestParam("listName[]") ArrayList<String> listName,
                             @RequestParam("listAviary[]") ArrayList<String> listAviary,
                             @RequestParam("listIsPredator[]") ArrayList<String> listIsPredator,
                             @RequestParam("countAviary") String countAviary,
                             @RequestParam("nameLocation") String nameLocation){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        LocationJDBC jdbcTemplateLocation =
                (LocationJDBC) context.getBean("jdbcTemplateLocation");
        if (jdbcTemplateLocation.getObj(nameLocation) != null){
           return Ajax.errorResponse("Такое имя уже существует");
        }

        int countAviaryforLocation = Integer.parseInt(countAviary);
        int[] counter = new int[countAviaryforLocation];
        int[] counter_local = new int[countAviaryforLocation];
        for(int i=0;i< listName.size();i++){
            int j = Integer.parseInt(listAviary.get(i));
            if(j!=0) {
                counter[j-1]++;
            }
            if(counter[j-1]>2){
                return Ajax.errorResponse("В вольер можно поместить не больше двух");
            }
            if(listIsPredator.get(i).equals("false") ){
                counter_local[j-1] = counter_local[j-1] - 2;
            }else{
                counter_local[j-1] = counter_local[j-1] + 3;
            }
            if(counter_local[j-1] == 1){
                return Ajax.errorResponse("Нельзя хищника размещать с травоядным");
            }
        }

        Location newLocation = new Location(nameLocation);
        jdbcTemplateLocation.save(newLocation);

        AviaryJDBC jdbcTemplateAviary =
                (AviaryJDBC) context.getBean("jdbcTemplateAviary");
        for(int i=0;i<countAviaryforLocation;i++){
            String arrayAnimals = "";
            for(int j=0;j<listName.size();j++){
                if(Integer.parseInt(listAviary.get(j))==i+1){
                    arrayAnimals=arrayAnimals + listName.get(j) + " ";
                }
            }
            String[] arrAnimals = arrayAnimals.split(" ");
            Aviary aviary = new Aviary(nameLocation,-1,i+1, arrAnimals);
            jdbcTemplateAviary.save(aviary);
        }

        return Ajax.successResponse(listName);
    }

}
