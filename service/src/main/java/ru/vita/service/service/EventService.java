package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vita.service.dto.event.EventCreateEditDto;
import ru.vita.service.dto.event.EventReadDto;
import ru.vita.service.mapper.create.EventCreateEditMapper;
import ru.vita.service.mapper.read.EventReadMapper;
import ru.vita.service.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final EventCreateEditMapper createMapper;

    private final EventReadMapper readMapper;

    /**
     * Возвращает все события из базы данных.
     *
     * @return список событий в виде DTO
     */
    public List<EventReadDto> findAll() {
        log.info("Делаем запрос в БД на получение всех событий");

        return eventRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Находит событие по уникальному идентификатору.
     *
     * @param id идентификатор события, не должен быть null
     * @return Optional с EventReadDto
     */
    public Optional<EventReadDto> findById(Integer id) {
        log.info("Делаем запрос в БД на получение события по id");

        return Optional.ofNullable(id)
                .flatMap(eventRepository::findById)
                .map(readMapper::map);
    }

    /**
     * Создает и сохраняет новое событие.
     *
     * @param eventDto данные для создания события
     * @return созданное событие
     * @throws IllegalArgumentException если eventDto равен null
     */
    @Transactional
    public EventReadDto create(EventCreateEditDto eventDto) {
        log.info("Делаем запрос в БД на создание события");

        return Optional.ofNullable(eventDto)
                .map(createMapper::map)
                .map(eventRepository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Dto создания события не может быть null"));
    }

    /**
     * Обновляет существующее событие.
     *
     * @param id идентификатор события которое хотим обновить
     * @param eventDto данные для обновления события
     * @return Optional с EventReadDto, где EventReadDto - обновленное событие или return Optional.empty();
     * если id или eventDto = null
     */
    @Transactional
    public Optional<EventReadDto> update(Integer id, EventCreateEditDto eventDto) {
        log.info("Делаем запрос в БД на обновление события");

        if (id == null || eventDto == null) {
            return Optional.empty();
        }

        return eventRepository.findById(id)
                .map(event -> createMapper.map(eventDto, event))
                .map(eventRepository::saveAndFlush)
                .map(readMapper::map);
    }

    /**
     * Удаляет событие.
     *
     * @param id идентификатор события которое хотим удалить
     * @return boolean. true - событие было удалено, false - событие не было удалено
     */
    @Transactional
    public boolean delete(Integer id) {
        log.info("Делаем запрос в БД на удаление события");
        return eventRepository.findById(id)
                .map(event -> {
                    eventRepository.delete(event);
                    eventRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
