package controllers;

import jdbc.AviaryJDBC;
import models.Aviary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DetailLocationController {
    @GetMapping("/detail-location/{name}")
    public String getAviary(Model model, @PathVariable("name") String name) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AviaryJDBC jdbcTemplateLocation =
                (AviaryJDBC) context.getBean("jdbcTemplateAviary");

        model.addAttribute("aviarys", jdbcTemplateLocation.getAllForLocation(name));
        List<Aviary> av = jdbcTemplateLocation.getAllForLocation(name);
        return "detail_location";
    }
}
