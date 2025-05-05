package ru.sbercraft.service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Data
@AllArgsConstructor
public class EventReadDto {
    Integer id;
    String name;
    CategoryEvent category;
}
