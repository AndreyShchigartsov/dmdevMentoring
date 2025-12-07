package ru.vita.service.integration.repository.querydsl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.excursion.ExcursionsFilter;
import ru.vita.service.entity.Excursion;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.StructureDivisionRepository;
import ru.vita.service.repository.querydsl.FilterExcursionRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
class FilterExcursionRepositoryImplIT extends IntegrationTestBase {

    private final FilterExcursionRepositoryImpl filterExcursionRepository;
    private final StructureDivisionRepository structureDivisionRepository;

    @Test
    void checkThatGetAllEvent() {
    }

    @Test
    void checkThatGetExcursionsName() {
        Excursion event = filterExcursionRepository.findByName("Квадрациклы");

        assertEquals("Квадрациклы", event.getService());
        assertEquals(800, event.getPrice());
    }

    @Test
    void checkGetAllExcursions() {
        List<Excursion> excursions = filterExcursionRepository.findAll();

        assertEquals(6, excursions.size());
    }

    @Test
    void checkThatGetAllExcursionsGtPrice() {
        List<Excursion> price1 = filterExcursionRepository.findPrice(700);//получаем все услуги дороже 600
        List<Excursion> price2 = filterExcursionRepository.findPrice(1000);//получаем все услуги дороже 600

        assertEquals(5, price1.size());
        assertEquals(1, price2.size());
    }

    @Test
    void checkThatGetExcursionsByStructure() {
        StructureDivision structureDivision = structureDivisionRepository.findById(1).get();
        List<Excursion> excursions = filterExcursionRepository.findAllQueryDslFilter(
                ExcursionsFilter.builder().structureDivision(structureDivision).build()
        );

        assertEquals(3, excursions.size());
    }
}