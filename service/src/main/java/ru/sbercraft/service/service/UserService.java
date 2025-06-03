package ru.sbercraft.service.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.dto.user.UserFilter;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.mapper.create.UserCreateEditMapper;
import ru.sbercraft.service.mapper.read.UserReadMapper;
import ru.sbercraft.service.repository.QPredicate;
import ru.sbercraft.service.repository.RoomRepository;
import ru.sbercraft.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singleton;
import static ru.sbercraft.service.entity.QUser.user;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final UserCreateEditMapper userCreateMapper;

    private final UserReadMapper userReadMapper;

//    private final CreateUserValidator createUserValidator;

    public List<UserReadDto> getUsersPage(Integer page, Integer size, Sort sort) {
        PageRequest pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable).stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Page<UserReadDto> getUsers(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getUsername(), user.username::containsIgnoreCase)
                .add(filter.getRole(), user.role::eq)
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public List<UserReadDto> getUsers() {
        return userRepository.users()
                .stream().map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public Optional<UserReadDto> getUser(UserReadDto user) {
        return userRepository.findByUsername(user.getUsername())
                .map(userReadMapper::map);
    }

    public UserReadDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(user -> userCreateMapper.map(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) /*throws ValidationException*/ {
//        ValidationResult valid = createUserValidator.isValid(user);
//        if (!valid.isValid()) {
//            throw new ValidationException(valid.getErrors());
//        }
        return Optional.of(user)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public List<UserReadDto> usersInRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow();

        return userRepository.findUsersInRoom(room).stream()
                .map(userReadMapper::map)
                .toList();
    }

    public List<UserReadDto> getUsersNotInRoom() {
        return userRepository.findUsersNotInRoom().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        return userRepository.findByUsername(login)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public List<UserReadDto> getUsersCamper() {
        return userRepository.findByUserRoleCamper(Role.CAMPER).stream()
                .map(userReadMapper::map)
                .toList();
    }
}
