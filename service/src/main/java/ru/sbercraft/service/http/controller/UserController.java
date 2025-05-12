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
        model.addAttribute("userReadDto", userReadDto);
        model.addAttribute("categories", Arrays.stream(CategoryEvent.values()).toList());
        return "user/home";
    }
}
