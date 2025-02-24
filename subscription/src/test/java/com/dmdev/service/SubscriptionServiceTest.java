package com.dmdev.service;

import com.dmdev.dao.SubscriptionDao;
import com.dmdev.dto.CreateSubscriptionDto;
import com.dmdev.entity.Provider;
import com.dmdev.entity.Status;
import com.dmdev.entity.Subscription;
import com.dmdev.exception.SubscriptionException;
import com.dmdev.exception.ValidationException;
import com.dmdev.mapper.CreateSubscriptionMapper;
import com.dmdev.validator.CreateSubscriptionValidator;
import com.dmdev.validator.Error;
import com.dmdev.validator.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dmdev.entity.Status.ACTIVE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {
    @Spy
    private Clock clock;//todo а что за реализация и как нужно было сделать?
    @Mock
    private SubscriptionDao dao;
    @Mock
    private CreateSubscriptionMapper mapper;
    @Mock
    private CreateSubscriptionValidator validator;
    @InjectMocks
    private SubscriptionService service;

    @Nested
    @DisplayName("Тесты метода upsert")
    class UpsertTest {

        @Test
        @DisplayName("Проверяем что все ок если все данные валидные")
        void checkMethodUpsertPositiveScenario() {
            CreateSubscriptionDto dto = createSubscription();
            doReturn(new ValidationResult()).when(validator).validate(dto);
            Subscription expectedResult = subscription(dto);
            doReturn(List.of(expectedResult)).when(dao).findByUserId(dto.getUserId());
            doReturn(expectedResult).when(dao).upsert(expectedResult);

            Subscription actualResult = service.upsert(dto);

            assertThat(actualResult).isEqualTo(expectedResult);
        }

        @Test
        @DisplayName("Проверяем что получаем исключение в случае невалидных данных")
        void checkMethodUpsertElseNotValid() {
            CreateSubscriptionDto dto = createSubscription();
            ValidationResult valid = new ValidationResult();
            valid.add(Error.of(400, "Not valid"));
            doReturn(valid).when(validator).validate(dto);

            assertThatThrownBy(() -> service.upsert(dto))
                    .isInstanceOf(ValidationException.class);
        }

        @Test
        @DisplayName("Проверяем что если в БД нет данный по UserId, " +
                "то возвращаем те данные, которые передели, только в смапленную в Subscription")
        void checkElseDBEmpty() {
            CreateSubscriptionDto dto = createSubscription();
            doReturn(new ValidationResult()).when(validator).validate(dto);
            Subscription expectedResult = subscription(dto);
            doReturn(Collections.EMPTY_LIST).when(dao).findByUserId(dto.getUserId());
            doReturn(expectedResult).when(dao).upsert(expectedResult);
            doReturn(expectedResult).when(mapper).map(dto);

            Subscription actualResult = service.upsert(dto);

            assertThat(actualResult).isEqualTo(expectedResult);
        }
    }

    @Nested
    @DisplayName("Тесты метода cancel")
    class CancelTest {

        @Test
        @DisplayName("Проверяем что все ок при актуальном статусе и если есть пользователь в БД по UserId")
        void checkPositiveScenario() {
            CreateSubscriptionDto dto = createSubscription();
            Subscription subscription = subscription(dto);
            doReturn(Optional.of(subscription)).when(dao).findById(dto.getUserId());

            service.cancel(dto.getUserId());

            verify(dao).update(subscription);
        }

        @Test
        @DisplayName("Проверяем что если в БД нету пользователя по соответствующему UserId, то выбрасываем исключение")
        void checkException() {
            CreateSubscriptionDto dto = createSubscription();
            doReturn(Optional.empty()).when(dao).findById(dto.getUserId());

            assertThatThrownBy(() -> service.cancel(dto.getUserId())).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(value = Status.class, names = {"CANCELED", "EXPIRED"})
        @DisplayName("Проверяем что со статусами CANCELED и EXPIRED выбрасываем исключение")
        void checkElseStatusNotActual(Status status) {
            CreateSubscriptionDto dto = createSubscription();
            Subscription subscription = subscription(dto);
            subscription.setStatus(status);
            doReturn(Optional.of(subscription)).when(dao).findById(dto.getUserId());

            assertThatThrownBy(() -> service.cancel(dto.getUserId())).isInstanceOf(SubscriptionException.class);
        }
    }

    @Nested
    @DisplayName("Тесты метода expire")
    class ExpireTest {

        @ParameterizedTest
        @EnumSource(value = Status.class, names = {"CANCELED", "ACTIVE"})
        @DisplayName("Проверяем что все отработает хорошо со статусами CANCELED и ACTIVE")
        void checkPositiveScenario(Status status) {
            CreateSubscriptionDto dto = createSubscription();
            Subscription subscription = subscription(dto);
            subscription.setStatus(status);
            doReturn(Optional.of(subscription)).when(dao).findById(dto.getUserId());

            service.expire(dto.getUserId());

            verify(dao).update(subscription);
        }

        @Test
        @DisplayName("Проверяем что получим исключение если статус EXPIRED")
        void checkElseStatusExpair() {
            CreateSubscriptionDto dto = createSubscription();
            Subscription subscription = subscription(dto);
            subscription.setStatus(Status.EXPIRED);
            doReturn(Optional.of(subscription)).when(dao).findById(dto.getUserId());

            assertThatThrownBy(() -> service.expire(dto.getUserId())).isInstanceOf(SubscriptionException.class);
        }
    }

    private Subscription subscription(CreateSubscriptionDto dto) {
        return Subscription.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .provider(Provider.valueOf(dto.getProvider()))
                .expirationDate(dto.getExpirationDate())
                .status(ACTIVE)
                .build();
    }

    private CreateSubscriptionDto createSubscription() {
        return CreateSubscriptionDto.builder()
                .userId(1)
                .name("Andrey")
                .provider(Provider.APPLE.name())
                .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                .build();
    }
}