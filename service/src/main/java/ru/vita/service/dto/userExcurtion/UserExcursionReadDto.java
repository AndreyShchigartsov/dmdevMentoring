package ru.vita.service.dto.userExcurtion;

import lombok.Builder;
import lombok.Value;
import ru.vita.service.entity.enums.PaymentMethod;
import ru.vita.service.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class UserExcursionReadDto {
    Long id;
    String username;
    String excursion;
    LocalDateTime purchaseDateTime;
    BigDecimal purchasePrice;
    PaymentStatus paymentStatus;
    PaymentMethod paymentMethod;
    String paymentReference;
    String notes;
}
