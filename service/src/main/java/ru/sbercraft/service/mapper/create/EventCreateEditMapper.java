package ru.sbercraft.service.mapper.create;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.event.EventCreateEditDto;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.mapper.Mapper;

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
