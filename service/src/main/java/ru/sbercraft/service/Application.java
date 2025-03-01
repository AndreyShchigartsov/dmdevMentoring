package ru.sbercraft.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.util.HibernateSessionFactory;

public class Application {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateSessionFactory.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User event1 = session.get(User.class, 1L);

            session.getTransaction().commit();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();

            User event2 = session1.get(User.class, 1L);

            session1.getTransaction().commit();
        }
    }
}
