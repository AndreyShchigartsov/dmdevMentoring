package ru.vita.service.integration.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.room.RoomCreateEditDto;
import ru.vita.service.dto.room.RoomReadDto;
import ru.vita.service.entity.Room;
import ru.vita.service.entity.User;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.RoomRepository;
import ru.vita.service.repository.UserRepository;
import ru.vita.service.service.RoomService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class RoomServiceIT extends IntegrationTestBase {

    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    void checkThatRoomsGetAllOk() {
        List<RoomReadDto> rooms = roomService.findAll();

        assertEquals(14, rooms.size());
    }

    @Test
    void checkThatGetRoomById() {
        RoomReadDto room = roomService.findById(1);

        assertEquals("3ий корпус(аврора)", room.getCorps());
        assertEquals(4, room.getSeatsValue());
        assertEquals(101, room.getRoomNumber());
    }

    @Test
    void checkThatRoomNotFound() {
        assertThrows(ResponseStatusException.class, () -> roomService.findById(99));
    }

    @Test
    void checkThatSuccessfullyCreatedRoom() {
        RoomCreateEditDto newRoom = createDto("Новый корпус", 123, 6, 3);

        RoomReadDto createdRoom = roomService.create(newRoom);

        entityManager.clear();
        assertEquals(15, roomRepository.findAll().size());
        assertEquals(6 ,createdRoom.getSeatsValue());
        assertEquals("Sun province" ,createdRoom.getProvinceId().getName());
        assertThrows(LazyInitializationException.class, () -> createdRoom.getProvinceId().getRooms().size());
    }

    @Test
    void checkThatDtoEmpty() {
        assertThrows(IllegalArgumentException.class, () -> roomService.create(null));
    }

    @Test
    void checkThatDeleteTrue() {
        boolean deleted = roomService.delete(1);

        assertTrue(deleted);
        assertEquals(13, roomRepository.findAll().size());
    }

    @Test
    @DisplayName("Проверяем что после удаления комнаты(в которой были пользователи), незаселенных пользователей стало больше" +
            " + проверяем что комната удаляется если в ней есть пользователи")
    void checkThatAfterDeleteTrueUsers() {
        List<User> uninhabitedUsersBeforeDeletedRoom = userRepository.findUsersNotInRoom();

        boolean deleted = roomService.delete(2);

        List<User> uninhabitedUsersAfterDeletedRoom = userRepository.findUsersNotInRoom();
        assertTrue(deleted);
        assertEquals(13, roomRepository.findAll().size());
        assertEquals(1, uninhabitedUsersBeforeDeletedRoom.size());
        assertEquals(3, uninhabitedUsersAfterDeletedRoom.size());
    }

    @Test
    void checkThatDeleteFalse() {
        boolean deleted = roomService.delete(99);

        assertFalse(deleted);
        assertEquals(14, roomRepository.findAll().size());
    }

    @Test
    void checkThatGetRoomByUsernameUser() {
        RoomReadDto room = roomService.getRoomByUser("Andrey");

        assertEquals("3ий корпус(аврора)", room.getCorps());
        assertEquals(4, room.getSeatsValue());
        assertEquals(101, room.getRoomNumber());
    }

    @Test
    void checkThatGetRoomByUsernameUserEmpty() {
        RoomReadDto roomNull = roomService.getRoomByUser("Alexandr");

        assertNull(roomNull);
    }

    @Test
    void checkThatGetExceptionIfNonExistingUser() {
        assertThrows(ResponseStatusException.class, () -> roomService.getRoomByUser("NonExisting"));
    }

    @Test
    void checkThatUserAddingInRoomOk() {
        User alexandr = userRepository.findByUsername("Alexandr")
                .orElseThrow(() -> new AssertionError("Test setup failed: user not found"));
        Room room = roomRepository.findById(1)
                .orElseThrow(() -> new AssertionError("Test setup failed: room not found"));

        roomService.addUserInRoom(room.getId(), alexandr.getId());

        assertNotNull(alexandr.getRoom());
        assertEquals("3ий корпус(аврора)", alexandr.getRoom().getCorps());
    }

    @Test
    void checkThatInRoomNotSeatsAndGetExceptionWhenTryingAddUser() {
        Room roomNotSeats = roomRepository.findById(2)
                .orElseThrow(() -> new AssertionError("Test setup failed: room not found"));
        User user = userRepository.findByUsername("Alexandr")
                .orElseThrow(() -> new AssertionError("Test setup failed: user not found"));

        assertThrows(ResponseStatusException.class, () -> roomService.addUserInRoom(roomNotSeats.getId(), user.getId()));
    }

    @Test
    void checkThatGetExceptionIfPassNotFountUserId() {
        assertThrows(ResponseStatusException.class, () -> roomService.addUserInRoom(2, 99L));
    }

    @Test
    void checkThatGetExceptionIfPassNotFountRoomId() {
        assertThrows(ResponseStatusException.class, () -> roomService.addUserInRoom(99, 2L));
    }

    @Test
    void checkThatSuccessfulRemoveUserFromRoom() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new AssertionError("Test setup failed: user not found"));
        assertNotNull(user.getRoom());

        roomService.removeUserFromRoom(1L);

        assertNull(user.getRoom());
    }

    private RoomCreateEditDto createDto(String corps, Integer roomNumber, Integer seatsValue, Integer provinceId) {
        return RoomCreateEditDto.builder()
                .corps(corps)
                .roomNumber(roomNumber)
                .seatsValue(seatsValue)
                .provinceId(provinceId)
                .build();
    }

//    @ParameterizedTest
//    @CsvSource({
//            "1, true, 1",
//            "99, false, 0"
//    })
//    void deleteShouldReturnCorrectResult(Integer id, boolean expectedResult, int expectedSize) {
//        int initialSize = roomRepository.findAll().size();
//
//        boolean deleted = roomService.delete(id);
//
//        assertEquals(expectedResult, deleted);
//        assertEquals(initialSize - expectedSize, roomRepository.findAll().size());
//    }
}