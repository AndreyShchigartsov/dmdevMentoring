package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}