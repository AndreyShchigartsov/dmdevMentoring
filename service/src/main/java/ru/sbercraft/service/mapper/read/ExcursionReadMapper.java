package ru.sbercraft.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.sbercraft.service.dto.extra.services.ExcursionReadDto;
import ru.sbercraft.service.entity.ExtraService;
import ru.sbercraft.service.mapper.Mapper;

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
