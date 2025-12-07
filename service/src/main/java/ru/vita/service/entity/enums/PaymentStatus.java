package ru.vita.service.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentStatus {
    CONFIRMED, CANCELLED, COMPLETED, REFUNDED, PENDING;

    public static Optional<PaymentStatus> find(String paymentStatus) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(paymentStatus))
                .findFirst();
    }
}
