package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.extra.services.ExcursionReadDto;
import ru.vita.service.entity.ExtraService;
import ru.vita.service.mapper.Mapper;

@Component
public class ExcursionReadMapper implements Mapper<ExtraService, ExcursionReadDto> {

    @Override
    public ExcursionReadDto map(ExtraService entity) {
        return ExcursionReadDto.builder()
                .id(entity.getId())
                .service(entity.getService())
                .price(entity.getPrice())
                .duration(entity.getDuration())
                .build();
    }
}
