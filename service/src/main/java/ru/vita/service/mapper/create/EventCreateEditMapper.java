package ru.vita.service.mapper.create;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.event.EventCreateEditDto;
import ru.vita.service.entity.Event;
import ru.vita.service.mapper.Mapper;

@Component
public class EventCreateEditMapper implements Mapper<EventCreateEditDto, Event> {

    @Override
    public Event map(EventCreateEditDto object) {
        return Event.builder()
                .name(object.getName())
                .category(object.getCategory())
                .build();
    }

    @Override
    public Event map(EventCreateEditDto fromObject, Event toObject) {
        toObject.setName(fromObject.getName());
        toObject.setCategory(fromObject.getCategory());
        return toObject;
    }
}
