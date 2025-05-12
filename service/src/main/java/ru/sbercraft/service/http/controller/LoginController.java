package ru.sbercraft.service.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.service.UserService;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    // todo оставил так как ток тут я реализовал логику с RedirectAttributes, что бы было куда посмотреть вслучае если забуду)
//    @PostMapping("/login")
//    public String homePage(Model model, UserReadDto userReadDto, RedirectAttributes redirectAttributes) {
//        return userService.getUser(userReadDto)
//                .map(user -> {
//                    redirectAttributes.addFlashAttribute("userReadDto", userReadDto);
//                    return "redirect:/user/home";
//                })
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/registration")
    public String registerPage() {
        return "user/registration";
    }

    @PostMapping("/registration")
    public String createUser(RedirectAttributes redirectAttributes, UserCreateEditDto user, BindingResult bindingResult) {
        try {
            userService.create(user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/registration";
        }
        return "redirect:/login";
    }
}
