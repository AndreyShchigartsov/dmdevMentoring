package ru.sbercraft.service.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercraft.service.dto.user.UserReadDto;
import ru.sbercraft.service.entity.enums.CategoryEvent;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/home")
    public String home(Model model, UserReadDto userReadDto) {
        // Что бы получить пользователя, как лучше сделать, отдельный запрос в БД делать?
        // Или специальный класс Autentication использовать и от туда доставать?
        // Или можно так и так, взависимоти от того, какие данные мне нужны? Просто мне нужен заполненый userReadDto
        // Я думаю запрос в БД нужен, верно?
        model.addAttribute("userReadDto", userReadDto);
        return "user/home";
    }
}
