package ru.sbercraft.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.dto.EventCreateEditDto;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.integration.IntegrationTestBase;
import ru.sbercraft.service.service.EventReadDto;
import ru.sbercraft.service.service.EventService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class EventServiceIT extends IntegrationTestBase {

    private final Integer ID_1 = 1;

    private final EventService eventService;

    @Test
    void findAll() {
        List<EventReadDto> events = eventService.findAll();

        assertThat(events.size()).isEqualTo(9);
    }

    @Test
    void findById() {
        Optional<EventReadDto> maybeEvent = eventService.findById(ID_1);

        assertTrue(maybeEvent.isPresent());
        maybeEvent.ifPresent(event -> {
            assertEquals("Баскетбол", event.getName());
            assertSame(CategoryEvent.SPORT, event.getCategory());
        });
    }

    @Test
    void create() {
        EventCreateEditDto eventCreateEditDto = createEvent("Биология", CategoryEvent.SPORT);

        EventReadDto eventReadDto = eventService.create(eventCreateEditDto);

        assertEquals(eventCreateEditDto.getName(), eventReadDto.getName());
        assertEquals(eventCreateEditDto.getCategory(), eventReadDto.getCategory());
        List<EventReadDto> events = eventService.findAll();
        assertThat(events.size()).isEqualTo(10);
    }

    @Test
    void update() {
        EventCreateEditDto eventDto = createEvent("Химия", CategoryEvent.SCIENCE);

        Optional<EventReadDto> actualResult = eventService.update(ID_1, eventDto);

        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(event -> {
            assertEquals(eventDto.getName(), event.getName());
            assertEquals(eventDto.getCategory(), event.getCategory());
        });
    }

    @Test
    void delete() {
        assertTrue(eventService.delete(ID_1));
        assertFalse(eventService.delete(-1));
    }

    private EventCreateEditDto createEvent(String name, CategoryEvent category) {
        return new EventCreateEditDto(
                name,
                category
        );
    }
}