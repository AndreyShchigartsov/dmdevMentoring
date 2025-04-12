package ru.sbercraft.service.integration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercraft.service.config.AppProperties;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.integration.annotation.IT;
import ru.sbercraft.service.service.TestService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@IT
public class TestServiceIT {

    private static final Integer EVENT_ID = 1;

    @Autowired
    private TestService testService;
    @Autowired
    private AppProperties appProperties;

    @Test
    void test() {
        Event event = createEvent();

        Optional<Event> actualResult = testService.getEvent(EVENT_ID);

        assertThat(actualResult.isPresent()).isEqualTo(true);
        actualResult.ifPresent(actual -> assertThat(actual).isEqualTo(event));
    }

    private Event createEvent() {
        return Event.builder()
                .id(1)
                .name("Шахматы фишера")
                .category(CategoryEvent.CHESS)
                .build();
    }
}
