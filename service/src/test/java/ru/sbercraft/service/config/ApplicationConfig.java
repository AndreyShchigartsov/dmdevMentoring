package ru.sbercraft.service.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.sbercraft.service.integration.HibernateTestUtil;

@Import(AppConfig.class)
public class ApplicationConfig {

//    @Bean
//    public SessionFactory sessionFactory() {
////        return HibernateTestUtil.buildSessionFactory();
//    }
}
