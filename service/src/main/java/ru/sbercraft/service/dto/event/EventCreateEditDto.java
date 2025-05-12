package ru.sbercraft.service.dto.event;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Value
@FieldNameConstants
public class EventCreateEditDto {
    String name;
    CategoryEvent category;
}
