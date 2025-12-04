package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vita.service.dto.extra.services.ExcursionCreateEditDto;
import ru.vita.service.dto.extra.services.ExcursionReadDto;
import ru.vita.service.mapper.create.ExcursionCreateEditMapper;
import ru.vita.service.mapper.read.ExcursionReadMapper;
import ru.vita.service.repository.ExtraServiceRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcursionService {

    private final ExtraServiceRepository repository;

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
    public Optional<ExcursionReadDto> findById(Integer id) {
        log.info("Делаем запрос в БД для получения экскурсии по id");

        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .map(readMapper::map);
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
