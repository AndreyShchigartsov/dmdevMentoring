package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.excursion.ExcursionCreateEditDto;
import ru.vita.service.dto.excursion.ExcursionReadDto;
import ru.vita.service.mapper.create.ExcursionCreateEditMapper;
import ru.vita.service.mapper.read.ExcursionReadMapper;
import ru.vita.service.repository.ExcursionRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcursionService {

    private final ExcursionRepository repository;

    private final ExcursionCreateEditMapper createMapper;

    private final ExcursionReadMapper readMapper;

    /**
     * Возвращает все экскурсии из базы данных
     *
     * @return список экскурсий в виде DTO
     */
    public List<ExcursionReadDto> findAll() {
        log.info("Делаем запрос в БД для получения всех экскурсий");

        return repository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает экскурсию по id
     *
     * @return Optional с ExcursionReadDto, где ExcursionReadDto - полученная экскурсия
     */
    public ExcursionReadDto findById(Integer id) {
        log.info("Делаем запрос в БД для получения экскурсии по id");

        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Эскурсия с таким id не найдена!"));
    }

    /**
     * Возвращает экскурсию по name
     *
     * @return Optional с ExcursionReadDto, где ExcursionReadDto - полученная экскурсия
     */
    public Optional<ExcursionReadDto> findByName(String name) {
        log.info("Делаем запрос в БД для получения экскурсии по названию");

        return Optional.ofNullable(name)
                .map(repository::findByName)
                .map(readMapper::map);
    }

    /**
     * Создает экскурсию
     *
     * @return ExcursionReadDto, где ExcursionReadDto - созданная экскурсия
     * @throws IllegalArgumentException если createEditDto равен null
     */
    @Transactional
    public ExcursionReadDto create(ExcursionCreateEditDto createEditDto) {
        log.info("Делаем запрос в БД для получения создания экскурсии");

        return Optional.ofNullable(createEditDto)
                .map(createMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Dto создания экскурсии не может быть null"));
    }

    /**
     * Удаляет экскурсию по id
     *
     * @return boolean. true - экскурсия удалена, false - экскурсия не удалена
     */
    @Transactional
    public boolean delete(Integer id) {
        return repository.findById(id)
                .map(event -> {
                    repository.delete(event);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }
}
