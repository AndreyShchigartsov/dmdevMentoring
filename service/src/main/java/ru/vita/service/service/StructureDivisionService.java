package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.structure.division.StructureDivisionCreateEditDto;
import ru.vita.service.dto.structure.division.StructureDivisionReadDto;
import ru.vita.service.entity.enums.Structure;
import ru.vita.service.mapper.create.StructureDivisionCreateEditMapper;
import ru.vita.service.mapper.read.StructureDivisionReadMapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StructureDivisionService {

    private final StructureDivisionRepository repository;

    private final StructureDivisionCreateEditMapper createMapper;

    private final StructureDivisionReadMapper readMapper;

    /**
     * Возвращает структуру по id
     *
     * @param id идентификатор структуры по которой осуществляем поиск
     * @return Optional с StructureDivisionReadDto, содержащим данные о структуре
     *         Если пользователь не относится ни к одной структуре, возвращается Optional.empty()
     */
    public Optional<StructureDivisionReadDto> findDyId(Integer id) {
        return repository.findById(id)
                .map(readMapper::map);
    }

    /**
     * Возвращает список всех структур
     *
     * @return List с StructureDivisionReadDto, содержащим данные о структуре
     */
    public List<StructureDivisionReadDto> getStructureDivisions() {
        return repository.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех структур по типу
     *
     * @param type тип структуры которые хотим вернуть
     * @return List с StructureDivisionReadDto, содержащим данные о структуре
     */
    public List<StructureDivisionReadDto> getStructureDivisions(Structure type) {
        return repository.listFindByStructure(type).stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает структуру к которой относится пользователь
     *
     * @param userId идентификатор пользователя
     * @return Optional с StructureDivisionReadDto, содержащим данные о структуре пользователя.
     *         Если пользователь не относится ни к одной структуре, возвращается Optional.empty()
     */
    public Optional<StructureDivisionReadDto> getStructureHaveUser(Long userId) {
        return repository.getStructureHaveUser(userId)
                .map(readMapper::map);
    }

    /**
     * Возвращает структуру и всех ее родителей по username
     *
     * @param username уникальное имя пользователя
     * @return Optional с StructureDivisionReadDto, содержащим данные о структуре пользователя.
     *         Если пользователь не относится ни к одной структуре, возвращается Optional.empty()
     */
    public Optional<StructureDivisionReadDto> getStructureHaveByUsername(String username) {
        return repository.getStructureHaveByUsername(username)
                .map(readMapper::map);
    }

    /**
     * Создает структуру
     *
     * @param divisionDto dto для создания структуры
     * @return созданную структуру в виде StructureDivisionReadDto
     * @throws ResponseStatusException если divisionDto null
     */
    @Transactional
    public StructureDivisionReadDto create(StructureDivisionCreateEditDto divisionDto) {
        return Optional.ofNullable(divisionDto)
                .map(createMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create structure division"));
    }

    /**
     * Обновляет структуру по id
     *
     * @param id структуры которую хотим обновить
     * @param divisionDto dto хранит данные на которые хотим обновить структуру по id
     * @return Optional с StructureDivisionReadDto, обновленная структура
     */
    @Transactional
    public Optional<StructureDivisionReadDto> update(Integer id, StructureDivisionCreateEditDto divisionDto) {
        return repository.findById(id)
                .map(structure -> createMapper.map(divisionDto, structure))
                .map(repository::saveAndFlush)
                .map(readMapper::map);
    }

    /**
     * Удаляем структуру по id
     *
     * @param id идентификатор структуры которую хотим удалить
     * @return Optional с StructureDivisionReadDto, структуру которую пытались удалить
     *         StructureDivisionReadDto вернется в любом случае, независимо от того удалили мы структуру или нет
     */
    @Transactional
    public Optional<StructureDivisionReadDto> delete(Integer id) {
        return repository.findById(id)
                .map(structure -> {
                    repository.delete(structure);
                    repository.flush();
                    return readMapper.map(structure);
                });
    }
}
