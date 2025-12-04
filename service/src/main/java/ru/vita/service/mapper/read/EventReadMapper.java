package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.entity.Event;
import ru.vita.service.dto.event.EventReadDto;
import ru.vita.service.mapper.Mapper;

@Component
public class EventReadMapper implements Mapper<Event, EventReadDto> {
    @Override
    public EventReadDto map(Event object) {
        return new EventReadDto(
                object.getId(),
                object.getName(),
                object.getCategory()
        );
    }
}
