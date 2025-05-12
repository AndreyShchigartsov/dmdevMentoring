package ru.sbercraft.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.repository.querydsl.FilterUserRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, FilterUserRepository, QuerydslPredicateExecutor<User> {

    Optional<User> findByUsername(String username);

    @Query("select u from User u " +
           "join fetch u.room ur " +
           "where u.room = :room")
    List<User> findUsersInRoom(@Param("room") Room room);

    @Query("select u from User u where u.room is null")
    List<User> findUsersNotInRoom();

    @Query("select u from User u where u.role = :role")
    List<User> findByUserRoleCamper(@Param("role") Role role);

    @EntityGraph(attributePaths = {"personalInformation"})
    @Query("select u from User u")
    List<User> users();

    @Query("select count(u) from User u where u.room = :room")
    Long getCountUserInRoom(@Param("room") Room room);

}