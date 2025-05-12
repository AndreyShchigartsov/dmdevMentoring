package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.mapper.create.UserCreateEditMapper;
import ru.sbercraft.service.mapper.read.UserReadMapper;
import ru.sbercraft.service.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singleton;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserCreateEditMapper userCreateMapper;

    private final UserReadMapper userReadMapper;

    public List<UserReadDto> getUsers() {
        return userRepository.findAll()
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

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(user -> userCreateMapper.map(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
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
}
