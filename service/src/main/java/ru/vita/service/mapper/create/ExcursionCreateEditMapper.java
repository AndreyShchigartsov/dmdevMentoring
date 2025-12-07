package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.excursion.ExcursionCreateEditDto;
import ru.vita.service.entity.Excursion;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExcursionCreateEditMapper implements Mapper<ExcursionCreateEditDto, Excursion> {

    private final StructureDivisionRepository repository;

    @Override
    public Excursion map(ExcursionCreateEditDto object) {
        return Excursion.builder()
                .structureDivision(getStructureDivision(object.getProvinceId()))
                .service(object.getService())
                .price(object.getPrice())
                .duration(Duration.ofMinutes(object.getDuration()).toMinutes())
                .build();
    }

    private StructureDivision getStructureDivision(Integer provinceId) {
        return Optional.of(provinceId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
