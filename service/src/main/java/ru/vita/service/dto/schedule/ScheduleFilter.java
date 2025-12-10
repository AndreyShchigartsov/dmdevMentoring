package ru.vita.service.dto.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.vita.service.entity.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ScheduleFilter {
    Long userId;
    Long createdUserId;
    LocalDate date;
    Status status;
}
