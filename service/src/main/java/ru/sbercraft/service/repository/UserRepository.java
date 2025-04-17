package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}