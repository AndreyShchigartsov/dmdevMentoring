package ru.sbercraft.service.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Status {
    CREATED, ACTIVE, READY;

    public static Optional<Status> find(String status) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(status))
                .findFirst();
    }
}
