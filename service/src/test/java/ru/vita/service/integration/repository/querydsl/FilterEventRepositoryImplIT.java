package ru.vita.service.integration.repository.querydsl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.event.EventFilter;
import ru.vita.service.entity.Event;
import ru.vita.service.entity.enums.CategoryEvent;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.querydsl.FilterEventRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class FilterEventRepositoryImplIT extends IntegrationTestBase {

    private final FilterEventRepositoryImpl filterEventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void checkThatGetAllEvent() {
        List<Event> allQueryDsl = filterEventRepository.findAllQueryDsl();

        Assertions.assertEquals(9, allQueryDsl.size());
    }

    @Test
    void checkThatGetOneEvent() {
        Event event = filterEventRepository.findByIdQueryDsl(1);

        Assertions.assertEquals("Баскетбол", event.getName());
    }

    @Test
    void checkThatGetEventsFilterCategory() {
        List<Event> event = filterEventRepository.findAllFilterNameAndCategory(
                EventFilter.builder().categoryEvent(CategoryEvent.SPORT).build()
        );

        Assertions.assertEquals(3, event.size());
    }

    @Test
    void checkThatElseGetScheduleReturnException() {
        List<Event> event = filterEventRepository.findAllFilterNameAndCategory(
                EventFilter.builder().name("Шахматы").build()
        );

        Assertions.assertEquals(1, event.size());
    }

    @Test
    void checkThatGetEventsFilterName() {
        List<Event> events = filterEventRepository.findAllFilterNameAndCategory(
                EventFilter.builder().name("Волейбол").build()
        );

        entityManager.clear();

        assertThrows(LazyInitializationException.class, () -> events.get(0).getSchedules().get(0));
    }


    @Test
    void checkThatWithEventGetAndSchedule() {
        List<Event> events = filterEventRepository.getEventWithSchedule();

        entityManager.clear();

        Event event = events.get(1);

        assertEquals(3, event.getSchedules().size());
        assertEquals(Status.ACTIVE, event.getSchedules().get(0).getStatus());
    }
}