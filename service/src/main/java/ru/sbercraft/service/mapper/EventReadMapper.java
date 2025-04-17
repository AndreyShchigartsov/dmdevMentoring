package ru.sbercraft.service.mapper;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.service.EventReadDto;

@Component
public class EventReadMapper implements Mapper<Event, EventReadDto>{

    @Override
    public EventReadDto map(Event object) {
        return new EventReadDto(
                object.getId(),
                object.getName(),
                object.getCategory()
        );
    }
}
