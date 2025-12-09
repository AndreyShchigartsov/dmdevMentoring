package ru.vita.service.http.controller.persons.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.room.RoomReadDto;
import ru.vita.service.service.RoomService;
import ru.vita.service.service.UserService;

import java.util.List;
import java.util.Objects;

@Controller("WorkerRoomController")
@RequestMapping("/worker/rooms")
@RequiredArgsConstructor
@SessionAttributes("rooms")
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;

    @GetMapping("/my-room")
    public String getMyRoom(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("room", roomService.getRoomByUser(userDetails.getUsername()));
        return "persons/worker/room/myRoom";
    }

    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "persons/worker/populate/populates";
    }

    @GetMapping("/{roomId}")
    public String getRoom(Model model, @PathVariable Integer roomId, @SessionAttribute("rooms") List<RoomReadDto> rooms) {
        RoomReadDto room = rooms.stream()
                .filter(r -> Objects.equals(r.getId(), roomId))
                .findFirst()
                .orElseThrow();
        model.addAttribute("room", room);
        model.addAttribute("users", userService.getUsersNotInRoom());
        model.addAttribute("usersInRoom", userService.getUsersInRoom(roomId));
        return "persons/worker/populate/populate";
    }

    @PostMapping("/populate/{roomId}/{userId}")
    public String addUserInRoom(RedirectAttributes redirectAttributes,
                                @PathVariable Integer roomId,
                                @PathVariable Long userId) {
        try {
            roomService.addUserInRoom(roomId, userId);
        } catch (ResponseStatusException e) {
            redirectAttributes.addFlashAttribute("error", e.getReason());
            return "redirect:/worker/rooms/" + roomId;
        }
        return "redirect:/worker/rooms/" + roomId;
    }

    @PostMapping("/populate/{roomId}/{userId}/delete")
    public String removeUserFromRoom(@PathVariable Integer roomId,
                                     @PathVariable Long userId) {
        roomService.removeUserFromRoom(userId);
        return "redirect:/worker/rooms/" + roomId;
    }
}
