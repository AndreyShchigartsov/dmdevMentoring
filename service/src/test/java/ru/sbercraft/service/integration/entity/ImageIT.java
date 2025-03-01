package ru.sbercraft.service.integration.entity;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.entity.Image;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.Worker;
import ru.sbercraft.service.entity.enums.JobPosition;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.integration.HibernateTestUtil;

import java.time.Instant;

class ImageIT {

    private static final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    private Session session;

    @BeforeEach
    void init() {
        session = sessionFactory.openSession();
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
    void checkThatWorkerPersist() {
        Image image = createImage();
        Worker worker = createUser();
        image.setUser(worker);
        session.persist(image);// с обычный Persisten сначала добавляет сущность в таблицу
        session.clear();

        Image responseImage = session.get(Image.class, 1L);

        Assertions.assertThat(responseImage).isEqualTo(image);
    }

    private Image createImage() {
        return Image.builder()
                .path("path/user1/img123")
                .build();
    }

    private Worker createUser() {
        return Worker.builder()
                .login("User1")
                .lastname("lastname")
                .password("pass")
                .registrationDate(Instant.now())
                .role(Role.USER)
                .salary(25000)
                .jobPosition(JobPosition.ASSISTANT)
                .active(true)
                .build();
    }

    private StructureDivision createStuctureDivision() {
        return StructureDivision.builder()
                .typeStructure(Structure.ORGANIZATIONAL)
                .name("Экскурсвита")
                .build();
    }
}