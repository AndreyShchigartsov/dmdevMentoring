package ru.sbercraft.service.integration.entity;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.entity.PersonalInformation;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.Worker;
import ru.sbercraft.service.entity.enums.JobPosition;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.integration.CreateDML;
import ru.sbercraft.service.integration.HibernateTestUtil;

import java.time.Instant;
import java.time.LocalDate;

class PersonalInformationIT {

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
    void checkThatSavePersonInfoAndWorker() {
        StructureDivision structureDivision = createStructureDivision();
        Worker worker = createWorker();
        PersonalInformation personalInformation = createPI();
        worker.setStructureDivision(structureDivision);
        personalInformation.setUser(worker);
        session.persist(structureDivision);
        session.persist(worker);
        session.persist(personalInformation);
        session.clear();

        PersonalInformation responsePersonalInformation = session.get(PersonalInformation.class, 1L);

        Assertions.assertThat(responsePersonalInformation.getUser()).isEqualTo(worker);
    }

    private StructureDivision createStructureDivision() {
        return StructureDivision.builder()
                .typeStructure(Structure.PROVINCE)
                .name("name")
                .build();
    }

    private Worker createWorker() {
        return Worker.builder()
                .login("login")
                .lastname("lastname")
                .password("pass")
                .registrationDate(Instant.now())
                .role(Role.USER)
                .salary(25000)
                .jobPosition(JobPosition.ASSISTANT)
                .active(true)
                .build();
    }

    private PersonalInformation createPI() {
        return PersonalInformation.builder()
                .passportData("123123 1234")
//                .user(user)
                .address("moskva/asdf")
                .birthCertificate("asdafaf")
                .birthDate(LocalDate.now())
                .build();
    }
}