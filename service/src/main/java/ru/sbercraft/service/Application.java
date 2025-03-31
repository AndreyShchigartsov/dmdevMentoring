package ru.sbercraft.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.sbercraft.service.config.AppProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
        AppProperties bean = context.getBean(AppProperties.class);
        System.out.println(context);
    }
}
