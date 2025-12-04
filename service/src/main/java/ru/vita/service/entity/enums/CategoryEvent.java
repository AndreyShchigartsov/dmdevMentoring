package ru.vita.service.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryEvent {
    SPORT, SCIENCE, CHESS;

    public static Optional<CategoryEvent> find(String categoryEvent) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(categoryEvent))
                .findFirst();
    }
}
