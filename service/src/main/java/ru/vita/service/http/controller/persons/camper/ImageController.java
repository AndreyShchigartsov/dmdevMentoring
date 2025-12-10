package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.PageResponse;
import ru.vita.service.dto.image.ImageCreateDto;
import ru.vita.service.dto.user.UserFilter;
import ru.vita.service.entity.enums.Role;
import ru.vita.service.service.ImageService;
import ru.vita.service.service.UserService;

@Controller("CamperImageController")
@RequestMapping("/camper/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    //todo добавить защиту что бы чужой пользователь вручную не смог поменять url и посмотреть чужие фото
    @GetMapping("/{userId}")
    public String getUser(Model model, @PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails) {
        userService.getUserByIdWithCheckUsername(userId, userDetails.getUsername())
                .map(user -> model.addAttribute("user", user));
        model.addAttribute("userId", userId);
        model.addAttribute("imagesId", imageService.findImagesIdByUserId(userId));
        return "persons/camper/image/addImage";
    }

    @PostMapping
    public String create(RedirectAttributes redirectAttributes, ImageCreateDto dto) {
        if (dto.getImage().getSize() == 0) {
            redirectAttributes.addFlashAttribute("errors", new ObjectError("NOT_FOUNT_EXCURSION", "Вы не выбрали фото"));
            return "redirect:/camper/images/" + dto.getUserId();
        }
        imageService.addImage(dto);
        return "redirect:/camper/images/" + dto.getUserId();
    }
}
