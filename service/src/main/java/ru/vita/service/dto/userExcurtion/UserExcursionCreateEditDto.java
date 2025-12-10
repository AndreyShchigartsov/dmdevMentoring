package ru.vita.service.dto.userExcurtion;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserExcursionCreateEditDto {

    @NotNull(message = "ID пользователя обязательно")
    @Positive(message = "ID пользователя должен быть положительным числом")
    private Long userId;

    @NotNull(message = "ID экскурсии обязательно")
    @Positive(message = "ID экскурсии должен быть положительным числом")
    private Integer excursionId;

    @NotNull(message = "Цена обязательно")
    @Positive(message = "Цена должна быть положительной")
    @Digits(integer = 10, fraction = 2, message = "Некорректный формат цены")
    private BigDecimal purchasePrice;

    @NotNull
    @Pattern(regexp = "CONFIRMED|CANCELLED|COMPLETED|REFUNDED|PENDING", message = "Недопустимый статус")
    private String paymentStatus;

    @NotNull(message = "Способ оплаты обязателен")
    @Pattern(regexp = "CARD|CASH|ONLINE", message = "Недопустимый способ оплаты")
    private String paymentMethod;

    @Size(max = 255, message = "Платежная ссылка не должна превышать 255 символов")
    private String paymentReference;

    @Size(max = 1000, message = "Примечание не должно превышать 1000 символов")
    private String notes;
}
