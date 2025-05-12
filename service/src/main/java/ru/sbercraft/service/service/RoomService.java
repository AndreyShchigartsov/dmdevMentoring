package ru.sbercraft.service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.dto.room.RoomCreateEditDto;
import ru.sbercraft.service.dto.room.RoomReadDto;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.mapper.create.RoomCreateEditMapper;
import ru.sbercraft.service.mapper.read.RoomReadMapper;
import ru.sbercraft.service.repository.RoomRepository;
import ru.sbercraft.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    private final RoomReadMapper roomReadMapper;

    private final RoomCreateEditMapper roomCreateEditMapper;

    public List<RoomReadDto> getRooms() {
        return roomRepository.findAll().stream()
                .map(roomReadMapper::map)
                .toList();
    }

    public RoomReadDto getRoom(Integer id) {
        return roomRepository.findById(id)
                .map(roomReadMapper::map)
                .orElseThrow();
    }

    public RoomReadDto getRoomByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return roomRepository.getRoomByUserId(user.getId())
                .map(roomReadMapper::map)
                .orElse(null);
    }

    @Transactional
    public RoomReadDto create(RoomCreateEditDto dto) {
        return Optional.of(dto)
                .map(roomCreateEditMapper::map)
                .map(roomRepository::save)
                .map(roomReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Integer id) {
        return roomRepository.findById(id)
                .map(room -> {
                    roomRepository.delete(room);
                    roomRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public void addUserInRoom(Integer roomId, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();
        Room room = roomRepository.findById(roomId)
                .orElseThrow();
        log.info("Осуществяем проверку на наличие мест в комнате!");
        if (!checkAvailabilitySpaceInRoom(room)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "В комнате нет места");
        }
        user.setRoom(room);
        userRepository.flush();
    }

    @Transactional
    public void removeUserFromRoom(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        log.info("Осуществяем удаление комнаты!");
        user.clearRoom();
        userRepository.save(user);
    }

    private boolean checkAvailabilitySpaceInRoom(Room room) {
        Long countUser = userRepository.getCountUserInRoom(room);
        return countUser < room.getSeatsValue();
    }
}
