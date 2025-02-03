package ru.sbercraft.common;

import org.junit.jupiter.api.*;
import ru.sbercraft.common.dto.User;
import ru.sbercraft.common.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ApplicationTest {

    UserService userService;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All: ");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Berfore Each: " + this);
        userService = new UserService();
    }

    @Test
    void checkListEmpty() {
        List<User> allUsers = userService.findAll();
        assertEquals(0, allUsers.size());
    }

    @Test
    void checkTest() {
        userService.add(new User());
        userService.add(new User());
        List<User> users = userService.findAll();
        System.out.println("checkTest: " + this + " service: " + userService);
        assertEquals(2, users.size());
    }

    @Test
    void checkTest2() {
        userService.add(new User());
        userService.add(new User());
        List<User> users = userService.findAll();
        System.out.println("checkTest2: " + this + " service: " + userService);
        assertEquals(2, users.size());// todo должно же быть 4 если используем PER_CLASS.
    }


    @AfterEach
    void afterEach() {
        System.out.println("After Each: " + this);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All: ");
    }
}