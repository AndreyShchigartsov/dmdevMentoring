package ru.vita.service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.Event;
import ru.vita.service.entity.Schedule;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.mapper.create.ScheduleCreateEditMapper;
import ru.vita.service.mapper.read.ScheduleReadMapper;
import ru.vita.service.repository.ScheduleRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private ScheduleCreateEditMapper createMapper;
    @Mock
    private ScheduleReadMapper readMapper;
    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void test() {
        Schedule entity = entity();
        ScheduleCreateEditDto scheduleDto = createScheduleDto();
        ScheduleReadDto scheduleReadDto = readScheduleDto();

        doReturn(entity).when(createMapper).map(scheduleDto);
        doReturn(entity).when(scheduleRepository).save(entity);
        doReturn(scheduleReadDto).when(readMapper).map(entity);

        ScheduleReadDto answer = scheduleService.create(scheduleDto);

        assertEquals(scheduleReadDto.getStatus(), answer.getStatus());
        assertEquals(scheduleReadDto.getUser(), answer.getUser());
        assertEquals(scheduleReadDto.getStructureDivision(), answer.getStructureDivision());
        assertEquals(scheduleReadDto.getEvent(), answer.getEvent());

        verify(createMapper).map(Mockito.any());
        verify(readMapper).map(Mockito.any());
        verify(scheduleRepository).save(Mockito.any());
    }

    private Schedule entity() {
        return Schedule.builder()
                .user(User.builder().username("Andrey").build())
                .structureDivision(StructureDivision.builder().name("Vita").build())
                .event(Event.builder().name("Баскетбол").build())
                .dateTime(LocalDateTime.parse("2025-11-08T03:09"))
                .status(Status.ACTIVE)
                .action("Hello")
                .build();
    }

    private ScheduleCreateEditDto createScheduleDto() {
        return ScheduleCreateEditDto.builder()
                .user("Andrey")
                .structureDivision("Vita")
                .event("Баскетбол")
                .dateTime("2025-11-08T03:09")
                .status(Status.ACTIVE)
                .action("Соревнования")
                .build();
    }

    private ScheduleReadDto readScheduleDto() {
        return ScheduleReadDto.builder()
                .user(User.builder().username("Andrey").build())
                .structureDivision(StructureDivision.builder().name("Vita").build())
                .event(Event.builder().name("Баскетбол").build())
                .dateTime(LocalDateTime.parse("2025-11-08T03:09"))
                .status(Status.ACTIVE)
                .action("Соревнования")
                .build();
    }
}