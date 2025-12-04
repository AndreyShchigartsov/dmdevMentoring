package ru.vita.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.schedule.ScheduleFilter;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.service.ScheduleService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ScheduleServiceIT extends IntegrationTestBase {

    private final ScheduleService scheduleService;

    @Test
    void checkFilterNotIsEmpty() {
        ScheduleFilter scheduleFilter = ScheduleFilter.builder()
                .date(LocalDate.parse("2025-11-10"))
                .status(Status.ACTIVE)
                .build();

        List<ScheduleReadDto> byFilter = scheduleService.findByFilter(scheduleFilter, "Valery");

        Assertions.assertFalse(byFilter.isEmpty());
    }

    @Test
    void checkFilterIsEmptySinceThereIsNoScheduleForDate() {
        ScheduleFilter scheduleFilter = ScheduleFilter.builder()
                .date(LocalDate.parse("2025-11-11"))
                .status(Status.ACTIVE)
                .build();

        List<ScheduleReadDto> byFilter = scheduleService.findByFilter(scheduleFilter, "Valery");

        Assertions.assertTrue(byFilter.isEmpty());
    }

    @Test
    void checkFilterIsEmptySinceSinceThereIsNoScheduleForTheUser() {
        ScheduleFilter scheduleFilter = ScheduleFilter.builder()
                .date(LocalDate.parse("2025-11-11"))
                .status(Status.ACTIVE)
                .build();

        List<ScheduleReadDto> byFilter = scheduleService.findByFilter(scheduleFilter, "Andrey");

        Assertions.assertTrue(byFilter.isEmpty());
    }
}
