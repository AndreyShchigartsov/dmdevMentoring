package ru.sbercraft.service.entity.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Structure {
    /*
    Порядок важен, важно распределять структуры от большего к меньшему
     */
    ORGANIZATIONAL, PROVINCE, GROUP;

    public static Optional<Structure> find(String structure) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(structure))
                .findFirst();
    }
}
