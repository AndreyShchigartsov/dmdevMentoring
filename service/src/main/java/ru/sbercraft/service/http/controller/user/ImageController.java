package ru.sbercraft.service.http.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercraft.service.dto.image.ImageCreateDto;
import ru.sbercraft.service.service.ImageService;
import ru.sbercraft.service.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsersCamper());
        return "user/image/users";
    }

    @GetMapping("/{userId}")
    public String getUser(Model model, @PathVariable Integer userId) {
        userService.getUserById(userId)
                .map(user -> model.addAttribute("user", user));
        model.addAttribute("userId", userId);
        model.addAttribute("imagesId", imageService.findImagesIdByUserId(userId));
        return "user/image/image";
    }

    @PostMapping
    public String create(ImageCreateDto dto) {
        imageService.addImage(dto);
        return "redirect:/user/images/" + dto.getUserId();
    }
}
