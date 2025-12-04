package ru.vita.service.dto.event;

import lombok.*;
import ru.vita.service.entity.enums.CategoryEvent;

@Value
@Builder
public class EventFilter {
    String name;
    CategoryEvent categoryEvent;
}
