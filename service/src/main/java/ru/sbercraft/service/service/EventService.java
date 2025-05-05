package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercraft.service.dto.event.EventCreateEditDto;
import ru.sbercraft.service.dto.event.EventReadDto;
import ru.sbercraft.service.mapper.create.EventCreateEditMapper;
import ru.sbercraft.service.mapper.read.EventReadMapper;
import ru.sbercraft.service.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventReadMapper eventReadMapper;
    private final EventCreateEditMapper eventCreateEditMapper;

    public List<EventReadDto> findAll() {
        return eventRepository.findAll().stream()
                .map(eventReadMapper::map)
                .toList();
    }

    public Optional<EventReadDto> findById(Integer id) {
        return eventRepository.findById(id)
                .map(eventReadMapper::map);
    }

    @Transactional
    public EventReadDto create(EventCreateEditDto event) {
        return Optional.of(event)
                .map(eventCreateEditMapper::map)
                .map(eventRepository::save)
                .map(eventReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<EventReadDto> update(Integer id, EventCreateEditDto eventDto) {
        return eventRepository.findById(id)
                .map(event -> eventCreateEditMapper.map(eventDto, event))
                .map(eventRepository::saveAndFlush)
                .map(eventReadMapper::map);
    }
    
    @Transactional
    public boolean delete(Integer id) {
        return eventRepository.findById(id)
                .map(event -> {
                    eventRepository.delete(event);
                    eventRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
