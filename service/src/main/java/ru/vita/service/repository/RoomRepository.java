package ru.vita.service.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vita.service.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findById(Integer id);

    void delete(Room room);

    @Query("select u.room from User u " +
            "where u.id = :userId")
    Optional<Room> getRoomByUserId(Long userId);

    @EntityGraph(attributePaths = {"structureDivision"})
    @Query("select r from Room r")
    List<Room> findAllWithStructure();
}
