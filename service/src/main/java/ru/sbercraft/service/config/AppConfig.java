package ru.sbercraft.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.yml")
@ComponentScan(basePackages = "ru.sbercraft.service")
@Configuration
public class AppConfig {

}
