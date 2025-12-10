package ru.vita.service.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.userExcurtion.UserExcursionCreateEditDto;
import ru.vita.service.dto.userExcurtion.UserExcursionReadDto;
import ru.vita.service.entity.UserExcursion;
import ru.vita.service.entity.enums.PaymentStatus;
import ru.vita.service.mapper.create.UserExcursionCreateEditMapper;
import ru.vita.service.mapper.read.UserExcursionReadMapper;
import ru.vita.service.repository.UserExcursionRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static ru.vita.service.entity.enums.PaymentStatus.CONFIRMED;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserExcursionService {

    private final UserExcursionRepository userExcursionRepository;

    private final UserExcursionCreateEditMapper createMapper;

    private final UserExcursionReadMapper readMapper;

    /**
     * Возвращает весь список экскурсий которые были добавлены когда-то на рассмотрение
     *
     * @return List<UserExcursionReadDto>, где UserExcursionReadDto хранит информацию об экскурсии
     */
    public List<UserExcursionReadDto> findAll() {
        return userExcursionRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает весь список экскурсий которые были добавлены когда-то на рассмотрение
     *
     * @param id идентификатор забронированной экскурсии
     * @return UserExcursionReadDto, где UserExcursionReadDto хранит информацию об экскурсии
     * @throws ResponseStatusException если забронированная экскурсия не найдена
     */
    public UserExcursionReadDto findById(Long id) {
        isValid(id);
        return Optional.of(id)
                .flatMap(userExcursionRepository::findById)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Экскурсия не найдена!"));
    }

    /**
     * Возвращает весь список не оплаченных экскурсий
     *
     * @return List<UserExcursionReadDto>, где UserExcursionReadDto хранит информацию о неоплаченной экскурсии
     */
    public List<UserExcursionReadDto> findAllNotPaidExcursion() {
        return userExcursionRepository.findAllByPaymentStatus(PaymentStatus.PENDING).stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает все экскурсии пользователя по его id
     *
     * @param userId идентификатор пользователя
     * @return List<UserExcursionReadDto>, где UserExcursionReadDto хранит информацию о купленной экскурсии
     * @throws ResponseStatusException если id не валидно
     */
    public List<UserExcursionReadDto> findAllExcursionByUserId(Long userId) {
        isValid(userId);
        return userExcursionRepository.findAllExcursionByUserId(userId).stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает все экскурсии пользователя по его username, username берем со SpringSecurity
     *
     * @return List<UserExcursionReadDto>, где UserExcursionReadDto хранит информацию о купленной экскурсии
     * @throws ResponseStatusException если id не валидно
     */
    public List<UserExcursionReadDto> findAllExcursionByUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userExcursionRepository.findAllExcursionByUsername(username).stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Создает заявку на покупку экскурсии
     *
     * @param createEditDto dto для покупки экскурсии
     * @return UserExcursionReadDto - созданную заявку на покупку экскурсии
     * @throws ResponseStatusException если createEditDto null, если id не валидно или если
     *                                 не найдена экскурсия или user
     */
    @Transactional
    public UserExcursionReadDto create(@Valid UserExcursionCreateEditDto createEditDto) {
        return Optional.ofNullable(createEditDto)
                .map(createMapper::map)
                .map(userExcursionRepository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "dto для оформления покупки экскурсии не может быть null!"));
    }

    /**
     * Переводит экскурсию в статус "Подтверждено", то есть оплачивает ее. Использует атомарный update и оптимистическую блокировку.
     *
     * @param id идентификатор неоплаченной экскурсии
     * @return boolean, true - экскурсия переведена в статус "Подтверждено", false - не переведена
     * @throws ResponseStatusException в случае невилидного id, когда экскурсия не найдена или уже оплачена
     */
    @Transactional
    public boolean payExcursionOptimisticLockAndAtomicUpdate(Long id) {
        isValid(id);
        UserExcursion userExcursion = userExcursionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Забронированная экскурсия ее найдена!"));

        if (userExcursionRepository.updateToConfirmedIfPending(id, userExcursion.getVersion()) == 1) {
            log.info("Экскурсия {} успешно оплачена!", id);
            return true;
        }

        log.info("Экскурсия {} не оплачена, выясняем причину!", id);
        if (userExcursion.getPaymentStatus() == CONFIRMED) {
            throw new ResponseStatusException(BAD_REQUEST, "Экскурсия уже оплачена!");
        }
        return false;
    }

    /**
     * Переводит экскурсию в статус "Подтверждено", то есть оплачивает ее. Использует пессимистическую блокировку
     *
     * @param id идентификатор неоплаченной экскурсии
     * @return boolean, true - экскурсия переведена в статус "Подтверждено"
     */
    @Transactional
    public boolean payExcursionPessimisticLock(Long id) {
        isValid(id);

        UserExcursion excursion = userExcursionRepository.findByIdWithLock(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Экскурсия не найдена"));

        return Optional.of(excursion)
                .filter(ue -> ue.getPaymentStatus() != CONFIRMED)
                .map(ue -> {
                    ue.setPaymentStatus(CONFIRMED);
                    log.info("Экскурсия {} оплачена", id);
                    return true;
                })
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Экскурсия уже оплачена!"));
    }

    private <T extends Number> void isValid(T id) {
        if (id == null) {
            throw new ResponseStatusException(BAD_REQUEST, "ID не может быть null!");
        }
        if (id.longValue() <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "ID должно быть больше нуля!");
        }
    }
}
