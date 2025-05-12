package ru.sbercraft.service.dto.event;

import lombok.Builder;
import lombok.Value;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Value
@Builder
public class EventFilter {
    private String name;
    private CategoryEvent categoryEvent;
}
