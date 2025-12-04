package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.entity.Event;
import ru.vita.service.entity.Schedule;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.User;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.EventRepository;
import ru.vita.service.repository.StructureDivisionRepository;
import ru.vita.service.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScheduleCreateEditMapper implements Mapper<ScheduleCreateEditDto, Schedule> {

    private final UserRepository userRepository;

    private final StructureDivisionRepository structureRepository;

    private final EventRepository eventRepository;

    @Override
    public Schedule map(ScheduleCreateEditDto createDto) {
        return Schedule.builder()
                .user(getUser(createDto.getUser()))
                .createdUser(getUser(createDto.getCreatedUser()))
                .structureDivision(getStructureDivision(createDto.getStructureDivision().isEmpty() ? null : createDto.getStructureDivision()))
                .event(getEvent(createDto.getEvent()))
                .dateTime(LocalDateTime.parse(createDto.getDateTime()))
                .status(createDto.getStatus())
                .action(createDto.getAction())
                .build();
    }


    private User getUser(String user) {
        return Optional.ofNullable(user)
                .flatMap(userRepository::findByUsername)
                .orElse(null);

    }

    private StructureDivision getStructureDivision(String structureDivision) {
        return Optional.ofNullable(structureDivision)
                .flatMap(structureRepository::findByName)
                .orElse(null);
    }

    private Event getEvent(String event) {
        return Optional.ofNullable(event)
                .flatMap(eventRepository::findByName)
                .orElse(null);
    }
}
