package ru.sbercraft.service.http.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercraft.service.dto.PageResponse;
import ru.sbercraft.service.dto.user.UserFilter;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Что бы получить пользователя, как лучше сделать, отдельный запрос в БД делать?
        // Или специальный класс Autentication использовать и от туда доставать?
        // Или можно так и так, взависимоти от того, какие данные мне нужны? Просто мне нужен заполненый userReadDto
        // Я думаю запрос в БД нужен, верно?
        model.addAttribute("user", service.getUserByUsername(userDetails.getUsername()));
        return "user/home";
    }

    @GetMapping("/campers")
    public String getCampers(Model model, UserFilter filter, Pageable pageable) {
        Role role = Role.CAMPER;
        filter.setRole(role);
        Page<UserReadDto> users = service.getUsers(filter, pageable);
        model.addAttribute("users", PageResponse.of(users));
        model.addAttribute("filter", filter);
        return "user/campers/campers";
    }
}
