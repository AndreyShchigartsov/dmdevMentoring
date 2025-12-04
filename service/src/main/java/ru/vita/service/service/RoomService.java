package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.room.RoomCreateEditDto;
import ru.vita.service.dto.room.RoomReadDto;
import ru.vita.service.entity.Room;
import ru.vita.service.entity.User;
import ru.vita.service.mapper.create.RoomCreateEditMapper;
import ru.vita.service.mapper.read.RoomReadMapper;
import ru.vita.service.repository.RoomRepository;
import ru.vita.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    private final RoomCreateEditMapper createMapper;

    private final RoomReadMapper readMapper;

    /**
     * Возвращает список всех комнат
     *
     * @return List<RoomReadDto>, где RoomReadDto - это комната
     */
    public List<RoomReadDto> findAll() {
        log.info("Делаем запрос в БД для получения всех комнат");

        return roomRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает комнату по id
     *
     * @param id индентификатор комнаты
     * @return RoomReadDto - комната
     */
    public RoomReadDto findById(Integer id) {
        log.info("Делаем запрос в БД для получения комнаты по id: {}", id);

        return roomRepository.findById(id)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Создает комнату по переданной dto
     *
     * @param dto - информация о команты которую хотим создать
     * @return RoomReadDto - созданная комната
     */
    @Transactional
    public RoomReadDto create(RoomCreateEditDto dto) {
        log.info("Делаем запрос в БД на создание комнаты");

        return Optional.ofNullable(dto)
                .map(createMapper::map)
                .map(roomRepository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Dto создания комнаты не может быть null"));
    }

    /**
     * Удаляет комнату по идентификатору
     *
     * @param id идентификатор комнаты
     * @return boolean. true - комната удалена. false - комната не удалена
     */
    @Transactional
    public boolean delete(Integer id) {
        log.info("Делаем запрос в БД на удаление комнаты");

        return roomRepository.findById(id)
                .map(room -> {
                    roomRepository.delete(room);
                    roomRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    /**
     * Возвращает комнату где проживает username
     *
     * @param username имя пользователя
     * @return RoomReadDto - комната
     * @throws ResponseStatusException если пользователя не существует
     */
    public RoomReadDto getRoomByUser(String username) {
        User user = findUserByUsernameOrThrow(username);

        log.info("Делаем запрос в БД для получения комнаты в которой проживает пользователь");

        return roomRepository.getRoomByUserId(user.getId())
                .map(readMapper::map)
                .orElse(null);
    }

    /**
     * Добавляет пользователя в комнату
     *
     * @param roomId идентификатор комнаты
     * @param userId идентификатор пользователя
     * @throws ResponseStatusException если пользователя или комнаты не существует
     */
    @Transactional
    public void addUserInRoom(Integer roomId, Long userId) {
        log.info("Проверка возможности размещения пользователя {} в комнате {}", userId, roomId);

        Room room = findRoomOrThrow(roomId);
        validateRoomAvailability(room);
        User user = findUserOrThrow(userId);

        user.setRoom(room);
    }

    /**
     * Удаляет комнату у пользователя, пользователь становится не заселенным
     *
     * @param userId идентификатор пользователя
     * @throws ResponseStatusException если пользователя не существует
     */
    @Transactional
    public void removeUserFromRoom(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        log.info("Осуществляем выселение пользователя из комнаты!");

        user.clearRoom();
        userRepository.save(user);
    }

    private Room findRoomOrThrow(Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private User findUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private User findUserByUsernameOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void validateRoomAvailability(Room room) {
        Long countUser = userRepository.getCountUserInRoom(room);
        if (countUser >= room.getSeatsValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "В комнате нет места");
        }
    }
}
