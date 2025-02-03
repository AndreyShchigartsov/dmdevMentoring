package ru.sbercraft.common.service;

import ru.sbercraft.common.dto.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public boolean add(User user) {
        return users.add(user);
    }
}
