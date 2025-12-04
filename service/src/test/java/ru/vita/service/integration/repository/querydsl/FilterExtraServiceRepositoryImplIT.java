package ru.vita.service.integration.repository.querydsl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.extra.services.ExtraServicesFilter;
import ru.vita.service.entity.ExtraService;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.StructureDivisionRepository;
import ru.vita.service.repository.querydsl.FilterExtraServiceRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
class FilterExtraServiceRepositoryImplIT extends IntegrationTestBase {

    private final FilterExtraServiceRepositoryImpl filterExtraServiceRepository;
    private final StructureDivisionRepository structureDivisionRepository;

    @Test
    void checkThatGetAllEvent() {
    }

    @Test
    void checkThatGetExtraServicesName() {
        ExtraService event = filterExtraServiceRepository.findByName("Квадрациклы");

        assertEquals("Квадрациклы", event.getService());
        assertEquals(800, event.getPrice());
    }

    @Test
    void checkGetAllExtraServices() {
        List<ExtraService> extraServices = filterExtraServiceRepository.findAll();

        assertEquals(6, extraServices.size());
    }

    @Test
    void checkThatGetAllExtraServicesGtPrice() {
        List<ExtraService> price1 = filterExtraServiceRepository.findPrice(700);//получаем все услуги дороже 600
        List<ExtraService> price2 = filterExtraServiceRepository.findPrice(1000);//получаем все услуги дороже 600

        assertEquals(5, price1.size());
        assertEquals(1, price2.size());
    }

    @Test
    void checkThatGetExtraServicesByStructure() {
        StructureDivision structureDivision = structureDivisionRepository.findById(1).get();
        List<ExtraService> extraServices = filterExtraServiceRepository.findAllQueryDslFilter(
                ExtraServicesFilter.builder().structureDivision(structureDivision).build()
        );

        assertEquals(3, extraServices.size());
    }
}