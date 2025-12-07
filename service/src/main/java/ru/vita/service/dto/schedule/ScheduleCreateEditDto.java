package ru.vita.service.dto.schedule;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.enums.Status;

@Value
@Builder
public class ScheduleCreateEditDto {

    String user;

    String createdUser;

    String structureDivision;

    String event;

    @NotEmpty
    String dateTime;

    Status status;

    String action;
}
