package ru.vita.service.dto.event;

import lombok.Value;
import ru.vita.service.entity.enums.CategoryEvent;

@Value
public class EventReadDto {
    Integer id;
    String name;
    CategoryEvent category;
}
