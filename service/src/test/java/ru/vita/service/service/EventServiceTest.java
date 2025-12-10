package ru.vita.service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vita.service.dto.event.EventReadDto;
import ru.vita.service.entity.Event;
import ru.vita.service.mapper.create.EventCreateEditMapper;
import ru.vita.service.mapper.read.EventReadMapper;
import ru.vita.service.repository.EventRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Spy
    private EventReadMapper eventReadMapper;
    @Mock
    private EventCreateEditMapper eventCreateEditMapper;
    @InjectMocks
    private EventService eventService;

    @Test
    void test1() {
        List<Event> list = List.of(new Event());
        doReturn(list).when(eventRepository).findAll();

        List<EventReadDto> all = eventService.findAll();

        Assertions.assertEquals(all.size(), 1);
    }

}