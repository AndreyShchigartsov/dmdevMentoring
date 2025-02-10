package com.dmdev.integration.dao;

import com.dmdev.dao.SubscriptionDao;
import com.dmdev.entity.Provider;
import com.dmdev.entity.Subscription;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.dmdev.entity.Status.ACTIVE;
import static org.assertj.core.api.Assertions.*;

public class SubscriptionDaoIT extends IntegrationTestBase {

    private final SubscriptionDao dao = SubscriptionDao.getInstance();

    @Test
    void findAll() {
        Subscription user1 = dao.insert(subscription(1, "Andrey"));
        Subscription user2 = dao.insert(subscription(1, "Artem"));
        Subscription user3 = dao.insert(subscription(2, "Denis"));

        List<Subscription> users = dao.findAll();

        assertThat(users).hasSize(3);
        List<Integer> ids = users.stream()
                .map(Subscription::getId).toList();
        assertThat(ids).contains(user1.getId(), user2.getId(), user3.getId());
    }

    @Test
    void findById() {
        Subscription user = dao.insert(subscription(1, "Andrey"));

        Optional<Subscription> userExist = dao.findById(user.getId());

        assertThat(userExist).isPresent();
    }

    @Test
    void findByIdNotFound() {
        Subscription user = dao.insert(subscription(1, "Andrey"));

        Optional<Subscription> userNotExist = dao.findById(user.getId() + 1);

        assertThat(userNotExist).isEmpty();
    }

    @Test
    void delete() {
        Subscription user = dao.insert(subscription(1, "Andrey"));

        boolean delete = dao.delete(user.getId());

        assertThat(delete).isEqualTo(true);
        Optional<Subscription> userNotExist = dao.findById(user.getId());
        assertThat(userNotExist).isEmpty();
    }

    @Test
    void update() {
        Subscription user = dao.insert(subscription(1, "Andrey"));
        Optional<Subscription> userExist = dao.findById(user.getId());
        assertThat(userExist).isPresent();
        user.setName("Denis");

        dao.update(user);

        Optional<Subscription> updateUser = dao.findById(user.getId());
        assertThat(updateUser).isPresent();
        assertThat(updateUser.get().getName()).isEqualTo("Denis");

    }

    @Test
    void insert() {
        Subscription andrey = dao.insert(subscription(1, "Andrey"));

        assertThat(andrey.getId()).isPositive();
        List<Subscription> users = dao.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    void findByUserId() {
        dao.insert(subscription(1, "Andrey"));
        dao.insert(subscription(2, "Artem"));
        dao.insert(subscription(2, "Denis"));

        List<Subscription> users = dao.findByUserId(2);

        assertThat(users).hasSize(2);
    }

    private Subscription subscription(Integer userId, String name) {
        return Subscription.builder()
                .userId(userId)
                .name(name)
                .provider(Provider.APPLE)
                .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                .status(ACTIVE)
                .build();
    }
}