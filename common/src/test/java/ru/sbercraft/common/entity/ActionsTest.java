package ru.sbercraft.common.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ActionsTest {

    Actions actions;

    @BeforeEach
    void setUp() {
        actions = new Actions();
    }

    @Test
    void checkGetterAndSetter() {
        String action = "hello";
        actions.setAction(action);
        Assertions.assertThat(actions.getAction()).isEqualTo(action);
    }
}