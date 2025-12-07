package ru.vita.service.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.user.UserCreateEditDto;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.dto.user.UserReadDto;
import ru.vita.service.entity.Room;
import ru.vita.service.entity.StructureDivision;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.exception.StructureDivisionNotFoundException;
import ru.vita.service.exception.UserNotFoundException;
import ru.vita.service.mapper.create.UserCreateEditMapper;
import ru.vita.service.mapper.read.UserReadMapper;
import ru.vita.service.repository.QPredicate;
import ru.vita.service.repository.RoomRepository;
import ru.vita.service.repository.StructureDivisionRepository;
import ru.vita.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singleton;
import static ru.vita.service.entity.QUser.user;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final StructureDivisionRepository structureRepository;

    private final RoomRepository roomRepository;

    private final UserCreateEditMapper createMapper;

    private final UserReadMapper readMapper;

    /**
     * Возвращает страницу по отфильтрованным пользователем
     *
     * @param filter фильтр по username и role
     * @param pageable информация о странице
     * @return Page с UserReadDto, UserReadDto - хранит данные о пользователе
     */
    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getRole(), user.role::eq)
                .addPredicate(user.role.ne(Role.ADMIN))
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(readMapper::map);
    }

    /**
     * Возвращает всех пользователей
     *
     * @return List с UserReadDto, UserReadDto - хранит данные о пользователе
     */
    public List<UserReadDto> findAll() {
        return userRepository.users()
                .stream().map(readMapper::map)
                .toList();
    }

    /**
     * Проверяет что данному id пользователя соответствует username
     *
     * @param userId идентификатор пользователя
     * @param username уникальное имя пользователя
     * @return Optional с UserReadDto, где UserReadDto хранит данные о пользователе которого проверяем с помощью username
     *         Если пользователь по username не найден, возвращается Optional.empty()
     * @throws AccessDeniedException если userId не соответствует username
     */
    public Optional<UserReadDto> getUserByIdWithCheckUsername(Long userId, String username) {
        userRepository.findByUsername(username)
                .filter(user -> user.getId().equals(userId))
                .orElseThrow(() -> {
                            log.warn("Доступ запрещен: {} → userId={}", username, userId);
                            return new AccessDeniedException("Доступ запрещен!");
                        }
                );

        return getUserById(userId);
    }

    /**
     * Возвращает пользователя по id
     *
     * @param id идентификатор пользователя
     * @return Optional с UserReadDto, где UserReadDto хранит данные о пользователе
     *         Если пользователь по id не найден, возвращается Optional.empty()
     */
    public Optional<UserReadDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(readMapper::map);
    }

    /**
     * Возвращает пользователя по username
     *
     * @param username уникальное имя пользователя
     * @return UserReadDto - хранит данные о пользователе
     */
    public UserReadDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден!"));
    }

    /**
     * Возвращает список пользователей живущих в комнате
     *
     * @param roomId идентификатор комнаты
     * @return List с UserReadDto, где UserReadDto - хранит данные о пользователе
     * @throws ResponseStatusException если комната не найдена
     */
    public List<UserReadDto> getUsersInRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Комната не найдена!"));

        return userRepository.findUsersInRoom(room).stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Возвращает всех незаселенных пользователей
     *
     * @return List с UserReadDto, где UserReadDto - хранит данные о пользователе
     */
    public List<UserReadDto> getUsersNotInRoom() {
        return userRepository.findUsersNotInRoom().stream()
                .map(readMapper::map)
                .toList();
    }

    /**
     * Обновляет пользователя
     *
     * @param id идентификатор пользователя которого хотим обновить
     * @param userDto новые данные о пользователе
     * @return Optional с UserReadDto, где UserReadDto хранит данные об обновленном пользователе
     *         Если пользователь по id не найден, возвращается Optional.empty()
     */
    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(user -> createMapper.map(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(readMapper::map);
    }

    /**
     * Создает пользователя
     *
     * @param userDto dto хранящий данные о пользователе
     * @return UserReadDto хранит данные созданного пользователя
     */
    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(createMapper::map)
                .map(userRepository::save)
                .map(readMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "userDto не должно быть пустым!"));
    }

    /**
     * Добавляет пользователя в структуру
     *
     * @param userId идентификатор пользователя
     * @param structureId идентификатор структуры
     * @throws UserNotFoundException когда пользователь не найден
     * @throws StructureDivisionNotFoundException когда структура не найдена
     */
    @Transactional
    public void addUserInStructureDivision(Long userId, Integer structureId) throws UserNotFoundException, StructureDivisionNotFoundException {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        StructureDivision structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new StructureDivisionNotFoundException(structureId));

        structure.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден!"));
    }
}