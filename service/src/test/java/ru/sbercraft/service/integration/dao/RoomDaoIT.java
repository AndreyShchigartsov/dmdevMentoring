package ru.sbercraft.service.integration.dao;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.sbercraft.service.config.AppProperties;
import ru.sbercraft.service.repository.RoomRepository;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.integration.IntegrationTestBase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class RoomDaoIT extends IntegrationTestBase {

    private final RoomRepository roomDao;
    private final EntityManager entityManager;
    private final AppProperties properties;

    @Test
    void delete() {
        Optional<Room> mayBeRoom = roomDao.findById(1);
        System.out.println("----------------------------after findById");
        assertTrue(mayBeRoom.isPresent());
        mayBeRoom.ifPresent(roomDao::delete);
        System.out.println("----------------------------before flush");
        entityManager.flush();
        System.out.println("----------------------------after flush");
        Optional<Room> mayBeRoom2 = roomDao.findById(1);
        System.out.println("---------------------------- after findById");
        assertTrue(mayBeRoom2.isEmpty());
    }
}