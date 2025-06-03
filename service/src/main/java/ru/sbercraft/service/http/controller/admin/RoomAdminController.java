package ru.sbercraft.service.http.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.dto.room.RoomCreateEditDto;
import ru.sbercraft.service.service.RoomService;
import ru.sbercraft.service.service.StructureDivisionService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;
    private final StructureDivisionService structureDivisionService;

    @GetMapping
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        model.addAttribute("parentStructures", structureDivisionService.getListStructureDivision());
        return "admin/room/rooms";
    }

    @GetMapping("/create")
    public String getRoomAdd(Model model) {
        model.addAttribute("valuesSeats", List.of(1,2,3,4,5,6,7,8));
        model.addAttribute("parentStructures", structureDivisionService.getListStructureDivision());
        return "admin/room/room";
    }

    @PostMapping("/create")
    public String create(RoomCreateEditDto room) {
        return Optional.of(roomService.create(room))
                .map(user -> "redirect:/admin/rooms")
                .orElseThrow();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!roomService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/admin/rooms";
    }
}