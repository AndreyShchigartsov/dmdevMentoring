package ru.sbercraft.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbercraft.service.config.AppConfig;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context);
    }
}
