package ru.vita.service.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ru.vita.service.dto.ScheduleTest;
import ru.vita.service.dto.schedule.ScheduleCreateEditDto;
import ru.vita.service.dto.schedule.ScheduleFilter;
import ru.vita.service.dto.schedule.ScheduleReadDto;
import ru.vita.service.entity.User;
import ru.vita.service.mapper.create.ScheduleCreateEditMapper;
import ru.vita.service.mapper.read.ScheduleReadMapper;
import ru.vita.service.repository.QPredicate;
import ru.vita.service.repository.ScheduleRepository;
import ru.vita.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.vita.service.entity.QSchedule.schedule;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final IntegrationService integrationService;

    private final UserRepository userRepository;

    private final ScheduleCreateEditMapper createMapper;

    private final ScheduleReadMapper readMapper;

    /**
     * Возвращает страницу расписаний
     *
     * @param pageable параметры страницы
     * @return Page c ScheduleReadDto, где ScheduleReadDto расписание
     */
    public Page<ScheduleReadDto> listSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(readMapper::map);
    }

    /**
     * Возвращает расписание для пользователя(Достается только те расписания, к которому пользователь имеет отношение)
     *
     * @param filter фильтр по userId, createdUserId, date, status
     * @param username пользователь кто имеет отношение в расписанию
     * @return List с ScheduleReadDto, где ScheduleReadDto расписание
     * @throws UsernameNotFoundException если пользователь не найден
     */
//    todo проверяем что username совпадает с текущем пользователем
//    @PreAuthorize("#username == authentication.principal.username") (разобраться как это тестировать)
//    вообще username не стоит передавать в данный метод, можно брать username
//    из контекста => не нужна проверка что текущий username = текущий пользователь
    public List<ScheduleReadDto> findByFilter(ScheduleFilter filter, String username) {
        User user = validateUserAccess(username);

        Predicate predicate1 = getPredicateWithDefaultFilter(filter, user);

        return scheduleRepository.findAll(predicate1).stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Создает расписание
     *
     * @param scheduleCreateDto dto для создания расписания
     * @return ScheduleReadDto - созданное расписание
     */
    @Transactional
    public ScheduleReadDto create(ScheduleCreateEditDto scheduleCreateDto) {
        return Optional.of(scheduleCreateDto)
                .map(createMapper::map)
                .map(scheduleRepository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    /**
     * Удаляет расписание по id
     *
     * @param id идентификатор расписания которое хотим удалить
     * @return boolean. true - расписание удалено, false - расписание не удалено.
     */
    @Transactional
    public boolean delete(Long id) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    scheduleRepository.delete(schedule);
                    scheduleRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public Mono<ScheduleTest> integrationSchedules(Long id) {
        return integrationService.getDate(id);
    }

    private User findUserOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    private static Predicate getPredicateWithDefaultFilter(ScheduleFilter filter, User user) {
        filter.setUserId(user.getId());
        filter.setCreatedUserId(user.getId());

        return QPredicate.builder()
                .add(filter.getUserId(), schedule.user.id::eq)
                .add(filter.getCreatedUserId(), schedule.createdUser.id::eq)
                .buildOr();
    }

    private User validateUserAccess(String username) {
        String currentUsername = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        if (!currentUsername.equals(username)) {
            throw new AccessDeniedException("Вы можете запрашивать только свои расписания");
        }

        return findUserOrThrow(username);
    }
}
