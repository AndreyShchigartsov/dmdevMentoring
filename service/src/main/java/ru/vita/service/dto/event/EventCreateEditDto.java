package ru.vita.service.dto.event;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import ru.vita.service.entity.enums.CategoryEvent;

@Value
@Builder
@FieldNameConstants
public class EventCreateEditDto {

    @Size(min = 4, max = 32)
    String name;

    @NotNull
    CategoryEvent category;
}
