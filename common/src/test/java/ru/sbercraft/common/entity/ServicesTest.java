package ru.sbercraft.common.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicesTest {
    Services services;

    @BeforeEach
    void setUp() {
        services = new Services();
    }

    @Test
    void checkGetterAndSetter() {
        String service = "hello";
        services.setService(service);
        Assertions.assertThat(services.getService()).isEqualTo(service);
    }
}