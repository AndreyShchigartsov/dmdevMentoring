package ru.sbercraft.service.entity;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UsersTest {

    private static SessionFactory sessionFactory;

    private Session session;

    @BeforeAll
    static void beforeAll() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        sessionFactory = configuration.buildSessionFactory();
    }

    @BeforeEach
    void init() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @Test
    void saveUser() {
        Subdivision subdivision1 = Subdivision.builder()
                .subdivision("Экскурсвита")
                .build();
        User user = User.builder()
                .firstname("Петя")
                .lastname("Акугинов")
                .email("Shchigartsov@sberbank.ru")
                .password("Пароль")
                .role("Admin")
                .dateRegistration(LocalDateTime.now())
                .active(true)
                .subdivision(subdivision1)
                .build();

        session.persist(subdivision1);
        session.persist(user);
        User user1 = session.get(User.class, user.getId());
        Assertions.assertThat(user1.getEmail()).isEqualTo(user.getEmail());
    }

    @AfterEach
    void delete() {
        session.getTransaction().rollback();
        session.close();
    }

    @AfterAll
    static void afterAll() {
        sessionFactory.close();
    }
}