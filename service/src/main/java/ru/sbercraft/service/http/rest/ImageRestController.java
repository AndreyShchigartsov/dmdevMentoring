package ru.sbercraft.service.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.service.ImageService;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService service;

    @GetMapping("/{imageId}")
    public byte[] findImages(@PathVariable Integer imageId) {
        return service.findImages(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
