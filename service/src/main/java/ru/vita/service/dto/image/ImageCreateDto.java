package ru.vita.service.dto.image;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class ImageCreateDto {
    MultipartFile image;
    Long userId;
}
