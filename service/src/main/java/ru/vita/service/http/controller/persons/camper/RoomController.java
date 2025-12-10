package ru.vita.service.http.controller.persons.camper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vita.service.dto.room.RoomReadDto;
import ru.vita.service.service.RoomService;
import ru.vita.service.service.UserService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/camper/rooms")
@RequiredArgsConstructor
@SessionAttributes("rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/my-room")
    public String getMyRoom(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("room", roomService.getRoomByUser(userDetails.getUsername()));
        return "persons/common/room/myRoom";
    }
}
