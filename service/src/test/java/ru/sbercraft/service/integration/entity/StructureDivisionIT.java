package ru.sbercraft.service.integration.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.integration.CreateDML;
import ru.sbercraft.service.integration.HibernateTestUtil;

class StructureDivisionIT {

    private static SessionFactory sessionFactory;

    private Session session;

    @BeforeAll
    static void beforeAll() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

    @BeforeEach
    void init() {
        session = sessionFactory.openSession();
        CreateDML.createData(session);
        session.beginTransaction();
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
    @Test
    void saveStructureDivision() {
        //todo В будущем покрыть
    }
}