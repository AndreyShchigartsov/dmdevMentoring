package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.Schedule;
import ru.vita.service.mapper.Mapper;

@Component
public class ScheduleReadMapper implements Mapper<Schedule, ScheduleReadDto> {

    @Override
    public ScheduleReadDto map(Schedule entity) {
        return ScheduleReadDto.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .createdUser(entity.getCreatedUser())
                .structureDivision(entity.getStructureDivision())
                .event(entity.getEvent())
                .dateTime(entity.getDateTime())
                .status(entity.getStatus())
                .action(entity.getAction())
                .build();
    }
}
