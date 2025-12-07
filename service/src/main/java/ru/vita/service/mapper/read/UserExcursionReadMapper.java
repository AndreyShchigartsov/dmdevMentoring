package ru.vita.service.mapper.read;

import org.springframework.stereotype.Component;
import ru.vita.service.dto.userExcurtion.UserExcursionReadDto;
import ru.vita.service.entity.UserExcursion;
import ru.vita.service.mapper.Mapper;

@Component
public class UserExcursionReadMapper implements Mapper<UserExcursion, UserExcursionReadDto> {

    @Override
    public UserExcursionReadDto map(UserExcursion entity) {
        return UserExcursionReadDto.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .excursion(entity.getExcursion().getService())
                .purchaseDateTime(entity.getPurchaseDate())
                .purchasePrice(entity.getPurchasePrice())
                .paymentStatus(entity.getPaymentStatus())
                .paymentMethod(entity.getPaymentMethod())
                .paymentReference(entity.getPaymentReference())
                .notes(entity.getNotes())
                .build();
    }
}
