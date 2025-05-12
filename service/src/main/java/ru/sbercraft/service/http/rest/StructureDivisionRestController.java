package ru.sbercraft.service.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionCreateDto;
import ru.sbercraft.service.dto.structureDivision.StructureDivisionReadDto;
import ru.sbercraft.service.service.StructureDivisionService;

import java.util.List;

@RequestMapping("/api/v1/structure")
@RequiredArgsConstructor
public class StructureDivisionRestController {

    private final StructureDivisionService service;

    @GetMapping
    public ResponseEntity<StructureDivisionReadDto> getStructureDivision(Integer id) {
        return ResponseEntity.ok(service.getStructureDivision(id));
    }

    @GetMapping
    public ResponseEntity<List<StructureDivisionReadDto>> getListStructureDivision() {
        return ResponseEntity.ok(service.getListStructureDivision());
    }

    @PostMapping
    public ResponseEntity<StructureDivisionReadDto> create(StructureDivisionCreateDto division) {
        return null;
    }
}
