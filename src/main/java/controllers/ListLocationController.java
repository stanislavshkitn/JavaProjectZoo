package controllers;

import jdbc.LocationJDBC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ListLocationController {
    @GetMapping("/list-location")
    public String getLocation(Model model) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        LocationJDBC jdbcTemplateLocation =
                (LocationJDBC) context.getBean("jdbcTemplateLocation");

        model.addAttribute("locations", jdbcTemplateLocation.getAll());
        return "list_location";
    }
}
