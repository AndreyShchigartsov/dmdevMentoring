package ru.sbercraft.service;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sbercraft.service.dao.RoomDao;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.util.HibernateSessionFactory;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateSessionFactory.buildSessionFactory()) {
            RoomDao dao = new RoomDao(sessionFactory);

            int size = dao.findAll().size();
            int siz2e = dao.findAll().size();

            System.out.println(size);
        }
    }
}
