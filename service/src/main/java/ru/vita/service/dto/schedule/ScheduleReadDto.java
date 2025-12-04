package ru.vita.service.dto.schedule;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.Event;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Status;

import java.time.LocalDateTime;

@Value
@Builder
public class ScheduleReadDto {
    Long id;
    User user;
    User createdUser;
    StructureDivision structureDivision;
    Event event;
    LocalDateTime dateTime;
    Status status;
    String action;
}
