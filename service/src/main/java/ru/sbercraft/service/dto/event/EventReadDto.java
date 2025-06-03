package ru.sbercraft.service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Data
@AllArgsConstructor
public class EventReadDto {
    private Integer id;
    private String name;
    private CategoryEvent category;
}
