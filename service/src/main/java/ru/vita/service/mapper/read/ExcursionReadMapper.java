package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.excursion.ExcursionReadDto;
import ru.vita.service.entity.Excursion;
import ru.vita.service.mapper.Mapper;

@Component
public class ExcursionReadMapper implements Mapper<Excursion, ExcursionReadDto> {

    @Override
    public ExcursionReadDto map(Excursion entity) {
        return ExcursionReadDto.builder()
                .id(entity.getId())
                .service(entity.getService())
                .price(entity.getPrice())
                .duration(entity.getDuration())
                .build();
    }
}
