package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.userExcurtion.UserExcursionCreateEditDto;
import ru.vita.service.service.UserExcursionService;
import ru.vita.service.service.UserService;

@Controller("CamperUserExcursionController")
@RequestMapping("/camper/user-excursion")
@RequiredArgsConstructor
public class UserExcursionController {

    private final UserExcursionService userExcursionService;

    private final UserService userService;

    @GetMapping("/my-booking")
    public String getBooking(Model model) {
        model.addAttribute("bookings", userExcursionService.findAllExcursionByUsername());
        return "persons/camper/userExcursion/myBookings";
    }

    @PostMapping("/book")
    public String bookExcursion(RedirectAttributes redirectAttributes,
                                UserExcursionCreateEditDto createDto,
                                Authentication authentication) {
        createDto.setUserId(userService.getUserByUsername(authentication.getName()).getId());
        redirectAttributes.addFlashAttribute("createBooking", userExcursionService.create(createDto));
        return "redirect:/camper/user-excursion/my-booking";
    }
}
