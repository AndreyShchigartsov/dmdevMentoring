package ru.vita.service.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<UserReadDto> getUsersPage(Integer page, Integer size, Sort sort) {
        PageRequest pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable).stream()
                .map(readMapper::map)
                .toList();
    }

    public Page<UserReadDto> getUsers(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getRole(), user.role::eq)
                .addPredicate(user.role.ne(Role.ADMIN))
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(readMapper::map);
    }

    public List<UserReadDto> getUsers() {
        return userRepository.users()
                .stream().map(readMapper::map)
                .toList();
    }

    public Optional<UserReadDto> getUserByIdWithCheckUsername(Long userId, String username) {
        userRepository.findByUsername(username)
                .filter(user -> user.getId().equals(userId))
                .orElseThrow(() -> {
                            log.warn("Доступ запрещен: {} → userId={}", username, userId);
                            return new AccessDeniedException("Доступ запрещен");
                        }
                );

        return getUserById(userId);
    }

    public Optional<UserReadDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(readMapper::map);
    }

    public Optional<UserReadDto> getUser(UserReadDto user) {
        return userRepository.findByUsername(user.getUsername())
                .map(readMapper::map);
    }

    public UserReadDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(readMapper::map)
                .orElseThrow();
    }

    public List<UserReadDto> getUsersInRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow();

        return userRepository.findUsersInRoom(room).stream()
                .map(readMapper::map)
                .toList();
    }

    public List<UserReadDto> getUsersNotInRoom() {
        return userRepository.findUsersNotInRoom().stream()
                .map(readMapper::map)
                .toList();
    }

    public List<UserReadDto> getUsersCamper() {
        return userRepository.findByUserRole(Role.CAMPER).stream()
                .map(readMapper::map)
                .toList();
    }


    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(user -> createMapper.map(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(readMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) /*throws ValidationException*/ {
        return Optional.of(user)
                .map(createMapper::map)
                .map(userRepository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    @Transactional
    public void addStructureDivisionInUser(Long userId, Integer structureId) throws UserNotFoundException, StructureDivisionNotFoundException {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        StructureDivision structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new StructureDivisionNotFoundException(structureId));

        user.setStructureDivision(structure);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
