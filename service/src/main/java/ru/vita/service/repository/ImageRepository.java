package ru.vita.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vita.service.entity.Image;
import ru.vita.service.entity.User;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("select i from Image i where i.user = :user")
    List<Image> findAllByUserId(@Param("user") User user);
}