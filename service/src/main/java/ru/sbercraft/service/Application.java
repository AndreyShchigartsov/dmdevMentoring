package ru.sbercraft.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import ru.sbercraft.service.entity.Subdivision;
import ru.sbercraft.service.entity.User;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAnnotatedClass(Users.class);

        Subdivision subdivision = Subdivision.builder()
                .subdivision("Экскурсвита")
                .build();
        User user = User.builder()
                .firstname("Слава")
                .lastname("Слава")
                .email("Слава")
                .password("Слава")
                .role("Слава")
                .dateRegistration(LocalDateTime.now())
                .active(true)
                .subdivision(subdivision)
                .build();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            Users users = session.get(Users.class, 2);
            Subdivision subdivision1 = session.get(Subdivision.class, 1);
//            session.evict(users);

//            session.persist(user);

            session.getTransaction().commit();
        }
    }
}
