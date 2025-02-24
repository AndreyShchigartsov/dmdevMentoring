package ru.sbercraft.service.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTest {
    Service service;

    @BeforeEach
    void setUp() {
        service = new Service();
    }

    @Test
    void checkGetterAndSetter() {
        String service = "hello";
        this.service.setName(service);
        Assertions.assertThat(this.service.getName()).isEqualTo(service);
    }
}