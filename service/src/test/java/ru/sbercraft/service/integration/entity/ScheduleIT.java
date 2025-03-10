package ru.sbercraft.service.integration.entity;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.Schedule;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.entity.enums.Status;
import ru.sbercraft.service.integration.CreateDML;
import ru.sbercraft.service.integration.HibernateTestUtil;

import java.time.Instant;

class ScheduleIT {

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
    void checkThatSaveEventThenSaveSchedule() {
        Event event = createEvent("Волейбол", CategoryEvent.SPORT);
        Schedule schedule = createSchedule(Status.ACTIVE);
        schedule.setEvent(event);
        session.persist(schedule);
        session.clear();

        Event event1 = session.get(Event.class, 1L);

        Assertions.assertThat(event1).isEqualTo(event);
    }

    private Event createEvent(String name, CategoryEvent categoryEvent) {
        return Event.builder()
                .name(name)
                .category(categoryEvent)
                .build();
    }

    private Schedule createSchedule(Status status) {
        return Schedule.builder()
                .dateTime(Instant.now())
                .status(status)
                .build();
    }
}