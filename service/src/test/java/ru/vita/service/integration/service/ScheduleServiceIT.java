package ru.vita.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.dto.schedule.ScheduleFilter;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.ScheduleRepository;
import ru.vita.service.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ScheduleServiceIT extends IntegrationTestBase {

    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @Test
    void checkThatGetSchedulePageOk() {
        Pageable pageable = PageRequest.of(0, 5);

        Page<ScheduleReadDto> firstPage = scheduleService.listSchedules(pageable);
        Page<ScheduleReadDto> twoPage = scheduleService.listSchedules(pageable.next());

        assertEquals(2, firstPage.getTotalPages());
        assertEquals(7, firstPage.getTotalElements());
        assertEquals(5, firstPage.get().count());
        assertEquals(2, twoPage.get().count());
    }

    @Test
    @WithMockUser(username = "Slava")
    void checkThatGetAllScheduleByUsername() {
        List<ScheduleReadDto> schedules = scheduleService.findByFilter(ScheduleFilter.builder().build(), "Slava");

        assertEquals(5, schedules.size());
    }

    @Test
    @WithMockUser(username = "Andrey")
    void checkThatUsernameNotGetOtherPeopleData() {
        AccessDeniedException exception = assertThrows(AccessDeniedException.class, () -> scheduleService.findByFilter(ScheduleFilter.builder().build(), "Slava"));
        assertEquals("Вы можете запрашивать только свои расписания", exception.getMessage());
    }

    @Test
    void checkThatSuccessfulCreateSchedule() {
        ScheduleCreateEditDto createDto = createDto("Andrey", "Slava", String.valueOf(LocalDateTime.now()), Status.CREATED, "Планерка");
        int schedulesBeforeCreatedSchedule = scheduleRepository.findAll().size();

        ScheduleReadDto scheduleReadDto = scheduleService.create(createDto);

        assertEquals("Andrey", scheduleReadDto.getUser().getUsername());
        assertEquals("Slava", scheduleReadDto.getCreatedUser().getUsername());
        assertEquals(schedulesBeforeCreatedSchedule + 1, scheduleRepository.findAll().size());
    }

    @Test
    void checkThatGetExceptionIdCreatedUserNotFount() {
        ScheduleCreateEditDto createDto = createDto("Andrey", "notFound", String.valueOf(LocalDateTime.now()), Status.CREATED, "Планерка");

        assertThrows(DataIntegrityViolationException.class, () -> scheduleService.create(createDto));
    }

    @Test
    void checkThatGetExceptionIdUserNull() {
        ScheduleCreateEditDto createDto = createDto(null, "Slava", String.valueOf(LocalDateTime.now()), Status.CREATED, "Планерка");

        ScheduleReadDto scheduleReadDto = scheduleService.create(createDto);

        assertEquals(Status.CREATED, scheduleReadDto.getStatus());
    }

    @Test
    void checkThatGetExceptionIfDtoNull() {
        assertThrows(IllegalArgumentException.class, () -> scheduleService.create(null));
    }

    @Test
    void checkThatDeleteOk() {
        int schedulesBeforeDeletedSchedule = scheduleRepository.findAll().size();

        boolean delete = scheduleService.delete(1L);

        assertTrue(delete);
        assertEquals(schedulesBeforeDeletedSchedule - 1, scheduleRepository.findAll().size());
    }

    private ScheduleCreateEditDto createDto(String user, String createdUser, String dateTime, Status status, String action) {
        return ScheduleCreateEditDto.builder()
                .user(user)
                .createdUser(createdUser)
                .dateTime(dateTime)
                .status(status)
                .action(action)
                .build();
    }
}
