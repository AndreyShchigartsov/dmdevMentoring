package ru.sbercraft.service.integration.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    private EventDao eventDao = new EventDao(Event.class, sessionFactory);

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
    void checkThatReturnAllEvent() {
//        session = sessionFactory.openSession();
//        session.beginTransaction();
        //Почему когда транзакцию открыаем второй раз, то результат 0 в тесте?

        List<Event> events = eventDao.findAll(session);

        assertThat(events.size()).isEqualTo(6);

//        session.getTransaction().commit();
//        session.close();
    }

    @Test
    void checkEventById() {
        Event event = eventDao.findById(session, 1);

        assertThat(event.getName()).isEqualTo("Баскетбол");
        assertThat(event.getCategory()).isEqualTo(CategoryEvent.SPORT);
    }

    @Test
    void checkEventFilterReturnOneValue() {
        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .build();

        List<Event> event = eventDao.findAllFilter(session, eventFilter);

        assertThat(event.size()).isEqualTo(1);
        assertThat(event.get(0).getName()).isEqualTo("Java");
        assertThat(event.get(0).getCategory()).isEqualTo(CategoryEvent.SCIENCE);
    }

    @Test
    void checkEventFilterReturnEmpty() {
        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .categoryEvent(CategoryEvent.SPORT)
                .build();

        List<Event> event = eventDao.findAllFilter(session, eventFilter);

        assertThat(event.size()).isEqualTo(0);
    }

    @Test
    void checkThatWithEventReturnAndSchedule() {
        List<Event> eventWithSchedule = eventDao.getEventWithSchedule(session);

        Optional<Event> mayBeEvent = eventWithSchedule.stream()
                .filter(event -> event.getName().equals("Баскетбол"))
                .findFirst();
        assertThat(mayBeEvent).isPresent();
        assertThat(mayBeEvent.get().getSchedules().size()).isEqualTo(2);
    }
}