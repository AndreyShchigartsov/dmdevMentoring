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
import ru.sbercraft.service.mapper.create.UserCreateMapper;
import ru.sbercraft.service.mapper.read.UserReadMapper;
import ru.sbercraft.service.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserCreateMapper userGuestCreateMapper;

    private final UserReadMapper userReadMapper;

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(userGuestCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Optional<UserReadDto> getUser(UserReadDto user) {
        return userRepository.findByUsername(user.getUsername())
                .map(userReadMapper::map);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsername(login)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
