package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.extra.services.ExcursionCreateEditDto;
import ru.sbercraft.service.dto.extra.services.ExcursionReadDto;
import ru.sbercraft.service.mapper.create.ExcursionCreateEditMapper;
import ru.sbercraft.service.mapper.read.ExcursionReadMapper;
import ru.sbercraft.service.repository.ExtraServiceRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ExcursionService {

    private final ExtraServiceRepository repository;

    private final ExcursionReadMapper excursionReadMapper;

    private final ExcursionCreateEditMapper excursionCreateEditMapper;

    public List<ExcursionReadDto> getExcursions() {
        return repository.findAll().stream()
                .map(excursionReadMapper::map)
                .toList();
    }

    public Optional<ExcursionReadDto> getExcursions(Integer id) {
        return repository.findById(id)
                .map(excursionReadMapper::map);
    }

    public ExcursionReadDto create(ExcursionCreateEditDto createEditDto) {
        return Optional.of(createEditDto)
                .map(excursionCreateEditMapper::map)
                .map(repository::save)
                .map(excursionReadMapper::map)
                .orElseThrow();
    }

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
