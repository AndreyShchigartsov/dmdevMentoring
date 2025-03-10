package ru.sbercraft.service.integration.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.dao.EventDao;
import ru.sbercraft.service.dto.EventFilter;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.integration.CreateDML;
import ru.sbercraft.service.integration.HibernateTestUtil;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class EventDaoTest {

    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    private Session session;

    private EventDao eventDao = EventDao.getInstance();

    @BeforeAll
    void beforeAll() {
        CreateDML.createData(sessionFactory);
    }

    @AfterAll
    void afterAll() {
        sessionFactory.close();
    }

    @Test
    void checkThatReturnAllEvent() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Event> events = eventDao.findAll(session);

        assertThat(events.size()).isEqualTo(6);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void checkEventById() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        Event event = eventDao.findById(session, 1);

        assertThat(event.getName()).isEqualTo("Баскетбол");
        assertThat(event.getCategory()).isEqualTo(CategoryEvent.SPORT);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void checkEventFilterReturnOneValue() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .build();

        List<Event> event = eventDao.findAllFilter(session, eventFilter);

        assertThat(event.size()).isEqualTo(1);
        assertThat(event.get(0).getName()).isEqualTo("Java");
        assertThat(event.get(0).getCategory()).isEqualTo(CategoryEvent.SCIENCE);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void checkEventFilterReturnEmpty() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .categoryEvent(CategoryEvent.SPORT)
                .build();

        List<Event> event = eventDao.findAllFilter(session, eventFilter);

        assertThat(event.size()).isEqualTo(0);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void checkThatWithEventReturnAndSchedule() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Event> eventWithSchedule = eventDao.getEventWithSchedule(session);

        session.getTransaction().commit();
        session.close();

        Optional<Event> mayBeEvent = eventWithSchedule.stream()
                .filter(event -> event.getName().equals("Баскетбол"))
                .findFirst();
        assertThat(mayBeEvent).isPresent();
        assertThat(mayBeEvent.get().getSchedules().size()).isEqualTo(2);
    }
}