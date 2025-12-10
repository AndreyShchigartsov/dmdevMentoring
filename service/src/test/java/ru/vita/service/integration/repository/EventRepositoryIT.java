package ru.vita.service.integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.repository.EventRepository;
import ru.vita.service.dto.event.EventFilter;
import ru.vita.service.entity.Event;
import ru.vita.service.entity.enums.CategoryEvent;
import ru.vita.service.integration.IntegrationTestBase;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class EventRepositoryIT extends IntegrationTestBase {

    private final EventRepository eventRepository;

    private final EntityManager entityManager;

    @Test
    void findById() {
        var company = entityManager.find(Event.class, 1);
        assertThat(company.getName()).isEqualTo("Баскетбол");
    }

    @Test
    void checkThatReturnAllEvent() {
        List<Event> events = eventRepository.findAll();

        assertThat(events.size()).isEqualTo(9);
    }

    @Test
    void checkEventById() {
        Optional<Event> event = eventRepository.findById(1);

        assertThat(event.get().getName()).isEqualTo("Баскетбол");
        assertThat(event.get().getCategory()).isEqualTo(CategoryEvent.SPORT);
    }

    @Test
    void checkEventFilterReturnOneValue() {
        EventFilter eventFilter = EventFilter.builder()
                .name("Баскетбол")
                .build();

        List<Event> event = eventRepository.findAllFilterNameAndCategory(eventFilter);

        assertThat(event.size()).isEqualTo(1);
        assertThat(event.get(0).getName()).isEqualTo("Баскетбол");
        assertThat(event.get(0).getCategory()).isEqualTo(CategoryEvent.SPORT);
    }

    @Test
    void checkEventFilterReturnEmpty() {
        EventFilter eventFilter = EventFilter.builder()
                .name("Java")
                .categoryEvent(CategoryEvent.SPORT)
                .build();

        List<Event> event = eventRepository.findAllFilterNameAndCategory(eventFilter);

        assertThat(event.size()).isEqualTo(0);
    }

    @Test
    void checkThatWithEventReturnAndSchedule() {
        List<Event> eventWithSchedule = eventRepository.getEventWithSchedule();

        Optional<Event> mayBeEvent = eventWithSchedule.stream()
                .filter(event -> event.getName().equals("Волейбол"))
                .findFirst();
        assertThat(mayBeEvent).isPresent();
        assertThat(mayBeEvent.get().getSchedules().size()).isEqualTo(3);
    }
}