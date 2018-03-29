package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        /*ApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-config.xml");

        AnimalsJDBC jdbcTemplateDeveloperDao =
                (AnimalsJDBC) context.getBean("jdbcTemplateDeveloperDao");

        System.out.println("========Developers List========");
        Animal a =new Animal("swerew5", true);

        jdbcTemplateDeveloperDao.save(a);*/
        //List developers = jdbcTemplateDeveloperDao.getAll();
        //System.out.println(developers);

        SpringApplication.run(Application.class, args);
    }

}
