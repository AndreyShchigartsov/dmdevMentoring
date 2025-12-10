package ru.vita.service.mapper.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.vita.service.dto.image.ImageCreateDto;
import ru.vita.service.entity.Image;
import ru.vita.service.entity.User;
import ru.vita.service.mapper.Mapper;
import ru.vita.service.repository.UserRepository;

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

    private User getUser(Long userId) {
        return Optional.of(userId)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
