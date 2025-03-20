package ru.sbercraft.service.integration.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbercraft.service.config.AppConfig;
import ru.sbercraft.service.dao.EventDao;
import ru.sbercraft.service.dto.EventFilter;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.integration.CreateDML;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class EventDaoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private final EventDao eventDao = context.getBean("eventDao", EventDao.class);

    private final Session session = context.getBean("session", Session.class);

    @BeforeEach
    void init() {
        CreateDML.createData(session);
        session.beginTransaction();
    }

    @AfterEach
    void delete() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    void checkThatReturnAllEvent() {
//        session = sessionFactory.openSession();
//        session.beginTransaction();
        //Почему когда транзакцию открыаем второй раз, то результат 0 в тесте?

        List<Event> events = eventDao.findAll();

        assertThat(events.size()).isEqualTo(6);
    }

    @Test
    void checkEventById() {
        Optional<Event> event = eventDao.findById(1);

        assertThat(event.get().getName()).isEqualTo("Баскетбол");
        assertThat(event.get().getCategory()).isEqualTo(CategoryEvent.SPORT);
    }

    @Test
    void checkEventFilterReturnOneValue() {
        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .build();

        List<Event> event = eventDao.findAllFilter(eventFilter);

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

        List<Event> event = eventDao.findAllFilter(eventFilter);

        assertThat(event.size()).isEqualTo(0);
    }

    @Test
    void checkThatWithEventReturnAndSchedule() {
        List<Event> eventWithSchedule = eventDao.getEventWithSchedule();

        Optional<Event> mayBeEvent = eventWithSchedule.stream()
                .filter(event -> event.getName().equals("Баскетбол"))
                .findFirst();
        assertThat(mayBeEvent).isPresent();
        assertThat(mayBeEvent.get().getSchedules().size()).isEqualTo(2);
    }
}