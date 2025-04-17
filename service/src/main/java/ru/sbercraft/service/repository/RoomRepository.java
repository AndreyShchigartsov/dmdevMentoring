package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findById(Integer id);

    void delete(Room room);
}
