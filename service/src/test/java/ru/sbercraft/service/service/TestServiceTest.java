package ru.sbercraft.service.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sbercraft.service.dao.EventDao;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.enums.CategoryEvent;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    private static final Integer EVENT_ID = 1;

    @Mock
    private EventDao eventDao;
    @InjectMocks
    private TestService testService;

    @Test
    void test() {
        Event event = createEvent();
        doReturn(Optional.of(event)).when(eventDao).findById(EVENT_ID);

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