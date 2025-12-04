package ru.vita.service.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.RoomRepository;
import ru.vita.service.repository.UserRepository;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
class UserRepositoryIT extends IntegrationTestBase {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Test
    void createUser() {
        User user = User.builder()
                .username("Antosha")
                .password("1234")
                .registrationDate(Instant.now())
                .active(true)
                .role(Role.CAMPER)
                .build();
        List<User> users = userRepository.findAll();

        User save = userRepository.save(user);

        List<User> usersBeforeSave = userRepository.findAll();
        Assertions.assertEquals(user.getUsername(), save.getUsername());
        Assertions.assertEquals(users.size() + 1, usersBeforeSave.size());
    }

    @Test
    void checkCountUsersInRoom() {
        roomRepository.findById(2).ifPresent(room -> {
            Long countUser = userRepository.getCountUserInRoom(room);
            Assertions.assertEquals(2, countUser);
        });
    }

    @Test
    void getUsersRole() {
        List<User> users = userRepository.findByUserRole(Role.USER);

        Assertions.assertEquals(3, users.size());
    }
}