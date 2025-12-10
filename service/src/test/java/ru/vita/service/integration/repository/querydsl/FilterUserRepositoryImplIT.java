package ru.vita.service.integration.repository.querydsl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.entity.enums.Status;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.querydsl.FilterUserRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImplIT extends IntegrationTestBase {

    private final FilterUserRepositoryImpl filterUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getAllUsers() {
        List<User> users = filterUserRepository.findAllQueryDsl();

        Assertions.assertEquals(5, users.size());
    }

    @Test
    void getUserById() {
        User user = filterUserRepository.findByIdQueryDsl(1L);

        Assertions.assertEquals("Andrey", user.getUsername());
    }

    @Test
    void checkAllUsersByRole() {
        UserFilter filter = UserFilter.builder().role(Role.USER).build();

        List<User> users = filterUserRepository.findAllFilter(filter);

        Assertions.assertEquals(3, users.size());
    }

    @Test
    void checkThatUserGetWithSchedule() {
        List<User> users = filterUserRepository.getUserWithSchedule();

        entityManager.clear();

        User andrey = users.get(0);

        Assertions.assertEquals(2, andrey.getSchedules().size());
        Assertions.assertEquals(Status.ACTIVE, andrey.getSchedules().get(0).getStatus());
    }
}
