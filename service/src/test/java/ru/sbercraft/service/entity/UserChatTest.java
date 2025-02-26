package ru.sbercraft.service.entity;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

class UserChatTest {

    @Test
    void test() {
        Configuration configuration = new Configuration();
        configuration.configure();

        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .firstname("Михаил Таль")
                .build();

        UserChat userChat = UserChat.builder()
                .build();

        userChat.setUser(user);

        session.getTransaction().commit();
    }

}