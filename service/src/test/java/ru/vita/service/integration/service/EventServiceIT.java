package ru.vita.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.event.EventCreateEditDto;
import ru.vita.service.entity.enums.CategoryEvent;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.dto.event.EventReadDto;
import ru.vita.service.service.EventService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class EventServiceIT extends IntegrationTestBase {

    private final Integer ID_1 = 1;

    private final EventService eventService;

    @Test
    void checkOkFindAll() {
        List<EventReadDto> events = eventService.findAll();

        assertThat(events.size()).isEqualTo(9);
    }

    @Test
    void checkOkFindById() {
        Optional<EventReadDto> maybeEvent = eventService.findById(ID_1);

        assertTrue(maybeEvent.isPresent());
        maybeEvent.ifPresent(event -> {
            assertEquals("Баскетбол", event.getName());
            assertSame(CategoryEvent.SPORT, event.getCategory());
        });
    }

    @Test
    void checkFindByIdWhereIdNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> eventService.findById(null));

        assertEquals("ID события не может быть null", exception.getMessage());
    }

    @Test
    void checkOkCreate() {
        EventCreateEditDto eventCreateEditDto = createEvent("Биология", CategoryEvent.SPORT);

        EventReadDto eventReadDto = eventService.create(eventCreateEditDto);

        assertEquals(eventCreateEditDto.getName(), eventReadDto.getName());
        assertEquals(eventCreateEditDto.getCategory(), eventReadDto.getCategory());
        List<EventReadDto> events = eventService.findAll();
        assertThat(events.size()).isEqualTo(10);
    }

    @Test
    void checkWhatCreateWhereEventEqualsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> eventService.create(null));

        assertEquals("Данные события не могут быть null", exception.getMessage());
    }

    @Test
    void checkOkUpdate() {
        EventCreateEditDto eventDto = createEvent("Химия", CategoryEvent.SCIENCE);

        Optional<EventReadDto> actualResult = eventService.update(ID_1, eventDto);

        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(event -> {
            assertEquals(eventDto.getName(), event.getName());
            assertEquals(eventDto.getCategory(), event.getCategory());
        });
    }

    @Test
    void checkWhereUpdateIdEventEqualsNull() {
        EventCreateEditDto eventDto = createEvent("Химия", CategoryEvent.SCIENCE);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> eventService.update(null, eventDto));

        assertEquals("ID события не может быть null", exception.getMessage());
    }

    @Test
    void checkWhereUpdateEventDtoEqualsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> eventService.update(ID_1, null));

        assertEquals("Данные события не могут быть null", exception.getMessage());
    }

    @Test
    void delete() {
        assertTrue(eventService.delete(ID_1));
        assertFalse(eventService.delete(-1));
    }

    private EventCreateEditDto createEvent(String name, CategoryEvent category) {
        return EventCreateEditDto.builder()
                .name(name)
                .category(category)
                .build();
    }
}