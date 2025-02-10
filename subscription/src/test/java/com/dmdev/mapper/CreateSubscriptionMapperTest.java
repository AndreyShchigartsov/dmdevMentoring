package com.dmdev.mapper;

import com.dmdev.dto.CreateSubscriptionDto;
import com.dmdev.entity.Provider;
import com.dmdev.entity.Subscription;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.dmdev.entity.Status.ACTIVE;
import static org.assertj.core.api.Assertions.*;

class CreateSubscriptionMapperTest {

    private final CreateSubscriptionMapper mapper = CreateSubscriptionMapper.getInstance();

    @Test
    void checkThatMapperWorkingOk() {
        CreateSubscriptionDto dto = createSubscription(Provider.APPLE.name());
        Subscription expectedResult = Subscription.builder()
                .id(null)
                .userId(dto.getUserId())
                .name(dto.getName())
                .provider(Provider.findByName(dto.getProvider()))
                .expirationDate(dto.getExpirationDate())
                .status(ACTIVE)
                .build();

        Subscription actualResult = mapper.map(dto);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkElseNotValidProvider() {
        CreateSubscriptionDto dto = createSubscription("Sber");

        Subscription actualResult = mapper.map(dto);

        assertThat(actualResult.getProvider()).isEqualTo(null);
        assertThat(actualResult.getStatus()).isEqualTo(ACTIVE);
    }

    private CreateSubscriptionDto createSubscription(String company) {
        return CreateSubscriptionDto.builder()
                .userId(1)
                .name("Andrey")
                .provider(company)
                .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                .build();
    }
}