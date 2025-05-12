package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sbercraft.service.dto.room.RoomReadDto;
import ru.sbercraft.service.entity.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findById(Integer id);

    void delete(Room room);

    @Query("select r from Room r " +
            "join fetch r.users ru " +
            "where ru.id = :userId")
    Optional<Room> getRoomByUserId(Long userId);
}
