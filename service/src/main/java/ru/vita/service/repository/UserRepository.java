package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import ru.vita.service.entity.Room;
import ru.vita.service.entity.User;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.repository.querydsl.FilterUserRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository, QuerydslPredicateExecutor<User> {

    Optional<User> findByUsername(String username);

    @Query("select u from User u " +
           "join fetch u.room ur " +
           "where u.room = :room")
    List<User> findUsersInRoom(@Param("room") Room room);

    @Query("select u from User u where u.room is null")
    List<User> findUsersNotInRoom();

    @Query("select u from User u where u.role = :role")
    List<User> findByUserRole(@Param("role") Role role);

    @Query("select u from User u")
    List<User> users();

    @Query("select count(u) from User u where u.room = :room")
    Long getCountUserInRoom(@Param("room") Room room);

}