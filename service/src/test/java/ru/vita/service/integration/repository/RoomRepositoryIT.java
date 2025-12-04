package ru.vita.service.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.vita.service.entity.Room;
import ru.vita.service.repository.RoomRepository;
import ru.vita.service.integration.IntegrationTestBase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class RoomRepositoryIT extends IntegrationTestBase {

    private final RoomRepository roomRepository;

    @Test
    void getRoomWithUser() {
        Optional<Room> roomWithUser = roomRepository.getRoomByUserId(2L);

        assertTrue(roomWithUser.isPresent());
        assertEquals(102, roomWithUser.get().getRoomNumber());
        assertEquals("3ий корпус(аврора)", roomWithUser.get().getCorps());
    }

    @Test
    void getRoomById() {
        Optional<Room> room = roomRepository.findById(1);

        assertTrue(room.isPresent());
        assertEquals(101, room.get().getRoomNumber());
    }
}