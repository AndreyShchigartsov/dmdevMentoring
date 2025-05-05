package ru.sbercraft.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.dto.event.EventReadDto;
import ru.sbercraft.service.mapper.Mapper;

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
