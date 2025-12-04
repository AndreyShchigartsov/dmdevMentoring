package ru.vita.service.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("Пользователь не найден с ID: " + id);
    }
}
