package ru.vita.service.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMethod {
    CARD, CASH, ONLINE;

    public static Optional<PaymentMethod> find(String paymentMethod) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(paymentMethod))
                .findFirst();
    }
}
