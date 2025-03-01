package ru.sbercraft.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import ru.sbercraft.service.entity.User;

import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();


            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), session.getEntityGraph("WithImageAndStructureDivision")
            );

            session.find(User.class, 1L, properties);

//            List<User> users = session.createQuery("select u from User u join fetch u.images", User.class)
//                    .list();
//
//            users.forEach(user -> System.out.println(user.getImages()));
//            users.forEach(user -> System.out.println(user.getStructureDivision()));
//            users.forEach(user -> System.out.println(user.getPersonalInformation()));

            session.getTransaction().commit();
        }
    }
}
