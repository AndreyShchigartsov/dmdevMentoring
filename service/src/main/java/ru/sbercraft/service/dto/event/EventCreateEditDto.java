package ru.sbercraft.service.dto.event;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import ru.sbercraft.service.entity.enums.CategoryEvent;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldNameConstants
public class EventCreateEditDto {
    @Size(min = 4, max = 32)
    private String name;
    @NotNull
    private CategoryEvent category;
}
