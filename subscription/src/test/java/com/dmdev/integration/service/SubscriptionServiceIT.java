package com.dmdev.integration.service;

import com.dmdev.dao.SubscriptionDao;
import com.dmdev.dto.CreateSubscriptionDto;
import com.dmdev.entity.Provider;
import com.dmdev.entity.Status;
import com.dmdev.entity.Subscription;
import com.dmdev.integration.IntegrationTestBase;
import com.dmdev.mapper.CreateSubscriptionMapper;
import com.dmdev.service.SubscriptionService;
import com.dmdev.validator.CreateSubscriptionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.dmdev.entity.Status.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionServiceIT extends IntegrationTestBase {

    private final SubscriptionDao dao = SubscriptionDao.getInstance();
    private SubscriptionService service;

    @BeforeEach
    void init() {
        service = new SubscriptionService(
                dao,
                CreateSubscriptionMapper.getInstance(),
                CreateSubscriptionValidator.getInstance(),
                Clock.systemUTC()
        );
    }

    @Test
    @DisplayName("Проверяем что пользователь сохранен в бд")
    void checkUpsertSave() {
        CreateSubscriptionDto dto1 = createSubscription(1, "Andrey");

        Subscription upsert = service.upsert(dto1);

        Optional<Subscription> user = dao.findById(upsert.getId());
        assertThat(user).isPresent();
        assertThat(user.get().getName()).isEqualTo(dto1.getName());
    }

    @Test
    @DisplayName("Проверяем что пользователь обновился по данному UserId в бд")
    void checkUpsertUpdate() {
        //todo
    }

    @Test
    @DisplayName("Проверяем что в БД изменился статус с ACTIVE на CANCELED")
    void checkCancel() {
        Subscription user = subscription(createSubscription(1, "Andrey"));
        Subscription insert = dao.insert(user);

        service.cancel(insert.getId());

        Optional<Subscription> mayBeUser = dao.findById(insert.getId());
        assertThat(mayBeUser).isPresent();
        assertThat(mayBeUser.get().getStatus()).isEqualTo(Status.CANCELED);

    }

    @Test
    @DisplayName("Проверяем что в БД изменился статус с ACTIVE на CANCELED")
    void checkExpire() {
        Subscription user = subscription(createSubscription(1, "Andrey"));
        Subscription insert = dao.insert(user);

        service.expire(insert.getId());

        Optional<Subscription> mayBeUser = dao.findById(insert.getId());
        assertThat(mayBeUser).isPresent();
        assertThat(mayBeUser.get().getStatus()).isEqualTo(Status.EXPIRED);
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

    private CreateSubscriptionDto createSubscription(Integer userId, String name) {
        return CreateSubscriptionDto.builder()
                .userId(userId)
                .name(name)
                .provider(Provider.APPLE.name())
                .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                .build();
    }
}
