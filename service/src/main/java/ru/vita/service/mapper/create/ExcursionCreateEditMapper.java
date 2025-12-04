package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vita.service.dto.extra.services.ExcursionCreateEditDto;
import ru.vita.service.entity.ExtraService;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.StructureDivisionRepository;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExcursionCreateEditMapper implements Mapper<ExcursionCreateEditDto, ExtraService> {

    private final StructureDivisionRepository repository;

    @Override
    public ExtraService map(ExcursionCreateEditDto object) {
        ExtraService extraService = ExtraService.builder()
                .structureDivision(getStructureDivision(object.getProvinceId()))
                .service(object.getService())
                .price(object.getPrice())
                .duration(Duration.ofMinutes(object.getDuration()).toMinutes())
                .build();
        return extraService;
    }

    private StructureDivision getStructureDivision(Integer provinceId) {
        return Optional.of(provinceId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
