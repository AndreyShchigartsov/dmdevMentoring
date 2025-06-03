package ru.sbercraft.service.integration.dao;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.repository.RoomRepository;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.integration.IntegrationTestBase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class RoomDaoIT extends IntegrationTestBase {

    private final RoomRepository roomRepository;
    private final EntityManager entityManager;

//    @Test
//    void delete() {
//        Optional<Room> mayBeRoom = roomRepository.findById(1);
//        System.out.println("----------------------------after findById");
//        assertTrue(mayBeRoom.isPresent());
//        mayBeRoom.ifPresent(roomRepository::delete);
//        System.out.println("----------------------------before flush");
//        entityManager.flush();
//        System.out.println("----------------------------after flush");
//        Optional<Room> mayBeRoom2 = roomRepository.findById(1);
//        System.out.println("---------------------------- after findById");
//        assertTrue(mayBeRoom2.isEmpty());
//    }
}