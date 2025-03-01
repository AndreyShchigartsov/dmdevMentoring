package ru.sbercraft.service.dto;

import lombok.Builder;
import lombok.Value;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Value
@Builder
public class EventFilter {
    String name;
    CategoryEvent categoryEvent;
}
