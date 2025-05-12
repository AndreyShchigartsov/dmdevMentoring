package ru.sbercraft.service.http.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sbercraft.service.dto.room.RoomReadDto;
import ru.sbercraft.service.service.RoomService;
import ru.sbercraft.service.service.UserService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user/rooms")
@RequiredArgsConstructor
@SessionAttributes("rooms")
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;

    @GetMapping("/myRoom")
    public String getMyRoom(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("room", roomService.getRoomByUser(userDetails.getUsername()));
        return "user/room/myRoom";
    }

    @GetMapping
    public String getRooms(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("rooms", roomService.getRooms());
        return "user/populate/populates";
    }

    @GetMapping("/{roomId}")
    public String getRoom(Model model, @PathVariable Integer roomId, @SessionAttribute("rooms") List<RoomReadDto> rooms) {
        RoomReadDto room = rooms.stream()
                .filter(r -> Objects.equals(r.getId(), roomId))
                .findFirst()
                .orElseThrow();
        model.addAttribute("room", room);
        model.addAttribute("users", userService.getUsersNotInRoom());
        model.addAttribute("usersInRoom", userService.usersInRoom(roomId));
        return "user/populate/populate";
    }

    @PostMapping("/populate/{roomId}/{userId}")
    public String addUserInRoom(RedirectAttributes redirectAttributes,
                                @PathVariable Integer roomId,
                                @PathVariable Integer userId) {
        try {
            roomService.addUserInRoom(roomId, userId);
        } catch (ResponseStatusException e) {
            redirectAttributes.addFlashAttribute("error", e.getReason());
            return "redirect:/user/rooms/" + roomId;
        }
        return "redirect:/user/rooms/" + roomId;
    }

    @PostMapping("/populate/{roomId}/{userId}/delete")
    public String removeUserFromRoom(@PathVariable Integer roomId,
                                     @PathVariable Integer userId) {
        roomService.removeUserFromRoom(userId);
        return "redirect:/user/rooms/" + roomId;
    }
}
