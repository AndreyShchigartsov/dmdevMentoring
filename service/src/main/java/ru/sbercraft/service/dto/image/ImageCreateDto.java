package ru.sbercraft.service.dto.image;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class ImageCreateDto {
    MultipartFile image;
    Integer userId;
}
