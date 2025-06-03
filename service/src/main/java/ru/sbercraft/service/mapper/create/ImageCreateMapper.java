package ru.sbercraft.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sbercraft.service.dto.image.ImageCreateDto;
import ru.sbercraft.service.entity.Image;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.mapper.Mapper;
import ru.sbercraft.service.repository.UserRepository;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class ImageCreateMapper implements Mapper<ImageCreateDto, Image> {

    private final UserRepository repository;

    @Override
    public Image map(ImageCreateDto object) {
        Image image = Image.builder()
                .user(getUser(object.getUserId()))
                .build();
        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(i -> image.setPath(i.getOriginalFilename()));
        return image;
    }

    private User getUser(Integer userId) {
        return Optional.of(userId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
