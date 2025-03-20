package ru.sbercraft.service.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sbercraft.service.util.HibernateSessionFactory;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.sbercraft.service")
@Configuration
public class AppConfig {

    @Bean
    public Session session(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateSessionFactory.buildSessionFactory();
    }
}
