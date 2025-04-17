package ru.sbercraft.service.repository;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    private static SessionFactory sessionFactory;

    private Session session;

    @BeforeAll
    static void beforeAll() {
//        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

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
    void checkThatEventAdding() {
        Event event = createEvent("Волейбол", CategoryEvent.SPORT);
        session.persist(event);
        session.evict(event);

        Event responseEvent = session.get(Event.class, 1L);

        assertThat(responseEvent).isEqualTo(event);
    }

    @Test
    void checkThatWorkingCascadePersist() {
        Schedule schedule = createSchedule();
        Event event = createEventWithSchedules("Волейбол", CategoryEvent.SPORT, schedule);
        session.persist(event);
        session.clear();

        Schedule responseSchedule = session.get(Schedule.class, 1L);

        assertThat(responseSchedule).isEqualTo(schedule);
    }

    @Test
    void checkThatWorkingCascadePersistWithResponseEvent() {
        Schedule schedule = createSchedule();
        Event event = createEvent("Волейбол", CategoryEvent.SPORT);
        event.addSchedules(schedule);
        session.persist(event);
        session.clear();

        Event responseEvent = session.get(Event.class, event.getId());

        assertThat(responseEvent.getSchedules().get(0)).isEqualTo(schedule);
    }

    @Test
    void checkThatWorkingCascadeRemove() {
        Schedule schedule = createSchedule();
        Event event = createEventWithSchedules("Волейбол", CategoryEvent.SPORT, schedule);
        session.persist(event);
        session.clear();
        Schedule responseSchedule = session.get(Schedule.class, schedule.getId());
        assertThat(responseSchedule).isEqualTo(schedule);
        Event eventResponse = session.get(Event.class, event.getId());
        eventResponse.addSchedules(responseSchedule);

        session.remove(eventResponse);

        Schedule responseScheduleRemove = session.get(Schedule.class, schedule.getId());
        assertThat(responseScheduleRemove).isEqualTo(null);
    }

    private Event createEvent(String name, CategoryEvent category) {
        return Event.builder()
                .name(name)
                .category(category)
                .build();
    }

    private Event createEventWithSchedules(String name, CategoryEvent category, Schedule schedule) {
        return Event.builder()
                .name(name)
                .category(category)
                .schedules(List.of(schedule))
                .build();
    }

    private Schedule createSchedule() {
        return Schedule.builder()
                .status(Status.CREATED)
                .build();
    }
}