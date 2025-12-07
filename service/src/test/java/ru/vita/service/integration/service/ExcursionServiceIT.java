package ru.vita.service.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.excursion.ExcursionCreateEditDto;
import ru.vita.service.dto.excursion.ExcursionReadDto;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.ExcursionRepository;
import ru.vita.service.service.ExcursionService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ExcursionServiceIT extends IntegrationTestBase {

    private final ExcursionService excursionService;
    private final ExcursionRepository excursionRepository;

    @Test
    void checkThatReturnAllListExcursion() {
        List<ExcursionReadDto> listExcursion = excursionService.findAll();

        assertEquals(6, listExcursion.size());
    }

    @Test
    void checkThatReturnExcursionById() {
        ExcursionReadDto excursion = excursionService.findById(1);

        assertEquals("Шоколадная фабрика", excursion.getService());
    }

    @Test
    void checkThatReturnEmptyExcursionById() {
        assertThrows(ResponseStatusException.class, () -> excursionService.findById(99));
    }

    @Test
    void checkThatReturnExcursionByName() {
        Optional<ExcursionReadDto> excursion = excursionService.findByName("Шоколадная фабрика");

        assertTrue(excursion.isPresent());
        assertEquals(1, excursion.get().getId());
    }

    @Test
    void checkThatReturnEmptyExcursionByName() {
        Optional<ExcursionReadDto> excursion = excursionService.findByName("Не существующая экскурсия");

        assertTrue(excursion.isEmpty());
    }

    @Test
    void checkThatCreatedExcursionOk() {
        ExcursionCreateEditDto newExcursion = createDto(1, "Новая экскурсия", 300, 100);
        int countBeforeCreated = excursionRepository.findAll().size();

        ExcursionReadDto excursionReadDto = excursionService.create(newExcursion);

        assertEquals("Новая экскурсия", excursionReadDto.getService());
        assertEquals(countBeforeCreated + 1, excursionService.findAll().size());
    }

    @Test
    void checkIfCreatedExcursionNotUnique() {
        ExcursionCreateEditDto notUniqueExcursion = createDto(1, "Шоколадная фабрика", 300, 100);

        assertThrows(DataIntegrityViolationException.class, () -> excursionService.create(notUniqueExcursion));
    }

    @Test
    void checkThatNotCreatedExcursionIfDtoNull() {
        int countBeforeCreated = excursionRepository.findAll().size();

        assertThrows(IllegalArgumentException.class, () -> excursionService.create(null));
        assertEquals(countBeforeCreated, excursionService.findAll().size());
    }

    @Test
    void checkDeleteExcursionByIdOk() {
        int countBeforeDeleted = excursionRepository.findAll().size();

        boolean isDeleted = excursionService.delete(6);

        assertTrue(isDeleted);
        assertEquals(countBeforeDeleted - 1, excursionService.findAll().size());
    }

    @Test
    void checkThatGetExceptionIfDeleteExcursionWhichRefersTableUserExcursion() {
        assertThrows(DataIntegrityViolationException.class, () -> excursionService.delete(1));
    }

    @Test
    void checkDeleteExcursionByIdNotOk() {
        int countBeforeDeleted = excursionRepository.findAll().size();

        boolean isDeleted = excursionService.delete(99);

        assertFalse(isDeleted);
        assertEquals(countBeforeDeleted, excursionService.findAll().size());
    }

    private ExcursionCreateEditDto createDto(Integer provinceId, String service, Integer price, Integer duration) {
        return ExcursionCreateEditDto.builder()
                .provinceId(provinceId)
                .service(service)
                .price(price)
                .duration(duration)
                .build();
    }
}