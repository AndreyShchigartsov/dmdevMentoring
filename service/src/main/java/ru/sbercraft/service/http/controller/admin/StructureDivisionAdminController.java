package ru.sbercraft.service.http.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercraft.service.dto.structure_division.StructureDivisionCreateEditDto;
import ru.sbercraft.service.dto.structure_division.StructureDivisionReadDto;
import ru.sbercraft.service.entity.enums.Structure;
import ru.sbercraft.service.service.StructureDivisionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

@Controller
@RequestMapping("/admin/structures")
@RequiredArgsConstructor
public class StructureDivisionAdminController {

    private final StructureDivisionService service;

    @GetMapping("/{structure}")
    public String getOrganizations(Model model, @PathVariable String structure) {
        model.addAttribute("nameStructure", structure);
        model.addAttribute("parentStructures", getParentStructure(structure));
        model.addAttribute("structures", service.getSeparateStructure(structure));
        return "admin/structure/structure";
    }

    @GetMapping
    public String getStructures(Model model) {
        model.addAttribute("structures", Arrays.stream(Structure.values()).map(structure -> structure.name().toLowerCase()).toList());
        return "admin/structure/structures";
    }

    @PostMapping
    public String create(StructureDivisionCreateEditDto structure) {
        service.create(structure);
        return "redirect:/admin/structures/" + structure.getTypeStructure().name();
    }

    @PostMapping("/{id}/update")
    public String update(Integer id, StructureDivisionCreateEditDto structure) {
        service.update(id, structure);
        return "redirect:/admin/structures/" + structure.getTypeStructure().name();
    }

    @PostMapping("/{id}/delete")
    public String delete(Integer id) {
        return service.delete(id).map(
                structure -> "redirect:/admin/structures/" + structure.getTypeStructure().name()
        ).orElse(null);
    }

    //стоит ли это вынести на уровень сервиса или в контроллере тоже можно подобное делать?
    private List<StructureDivisionReadDto> getParentStructure(String structure) {
        List<String> structures = Arrays.stream(Structure.values()).map(str -> str.name().toLowerCase()).toList();
        int index = structures.indexOf(structure);
        index--;
        if (index < 0) {
            return emptyList();
        } else {
            return service.getSeparateStructure(structures.get(index));
        }
    }
}
