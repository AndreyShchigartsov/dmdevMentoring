package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.userExcurtion.UserExcursionCreateEditDto;
import ru.vita.service.entity.Excursion;
import ru.vita.service.entity.User;
import ru.vita.service.entity.UserExcursion;
import ru.vita.service.entity.enums.PaymentMethod;
import ru.vita.service.entity.enums.PaymentStatus;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.ExcursionRepository;
import ru.vita.service.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserExcursionCreateEditMapper implements Mapper<UserExcursionCreateEditDto, UserExcursion> {

    private final UserRepository userRepository;
    private final ExcursionRepository excursionRepository;

    @Override
    public UserExcursion map(UserExcursionCreateEditDto createEditDto) {
        return UserExcursion.builder()
                .user(getUser(createEditDto.getUserId()))
                .excursion(getExcursion(createEditDto.getExcursionId()))
                .purchaseDate(LocalDateTime.now())
                .purchasePrice(createEditDto.getPurchasePrice())
                .paymentStatus(PaymentStatus.find(createEditDto.getPaymentStatus()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "PaymentStatus не верный!")))
                .paymentMethod(PaymentMethod.find(createEditDto.getPaymentMethod()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "PaymentMethod не верный!")))
                .paymentReference(createEditDto.getPaymentReference())
                .notes(createEditDto.getNotes())
                .build();
    }

    private User getUser(Long id) {
        return Optional.ofNullable(id)
                .map(this::isValid)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
    }

    private Excursion getExcursion(Integer id) {
        return Optional.ofNullable(id)
                .map(this::isValid)
                .flatMap(excursionRepository::findById)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Экскурсия не найдена"));
    }

    private <T extends Number> T isValid(T id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID не может быть null");
        }
        if (id.longValue() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID должно быть больше нуля");
        }
        return id;
    }
}
