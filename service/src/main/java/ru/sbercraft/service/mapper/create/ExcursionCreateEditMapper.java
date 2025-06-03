package ru.sbercraft.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.extra.services.ExcursionCreateEditDto;
import ru.sbercraft.service.entity.ExtraService;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.mapper.Mapper;
import ru.sbercraft.service.repository.StructureDivisionRepository;

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
