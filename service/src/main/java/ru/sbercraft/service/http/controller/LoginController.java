package ru.sbercraft.service.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sbercraft.service.dto.user.UserCreateEditDto;
import ru.sbercraft.service.exception.ValidationException;
import ru.sbercraft.service.service.UserService;
import ru.sbercraft.service.validation.validator.Error;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
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
        return "login/registration";
    }

    @PostMapping("/registration")
    public String createUser(RedirectAttributes redirectAttributes,
                             @Validated UserCreateEditDto user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }

//        try {
        userService.create(user);
        return "redirect:/login";
//        } catch (ValidationException e) {
//            redirectAttributes.addFlashAttribute("errors", e.getErrors().stream().map(Error::getMessage).toList());
//            return "redirect:/registration";
//        }
    }
}
