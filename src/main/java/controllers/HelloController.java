package controllers;

import jdbc.AnimalsJDBC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
public class HelloController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AnimalsJDBC jdbcTemplateAnimals =
                (AnimalsJDBC) context.getBean("jdbcTemplateAnimals");


        model.addAttribute("test", jdbcTemplateAnimals.getAll());
        return "greeting";
    }

}


