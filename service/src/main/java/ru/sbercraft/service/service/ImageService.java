package ru.sbercraft.service.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.sbercraft.service.dto.image.ImageCreateDto;
import ru.sbercraft.service.entity.Image;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.mapper.create.ImageCreateMapper;
import ru.sbercraft.service.repository.ImageRepository;
import ru.sbercraft.service.repository.UserRepository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:C:\\Users\\Andrey72\\Desktop\\work\\dmdevMentoring\\images}")
    private final String bucket;

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ImageCreateMapper imageCreateMapper;

    public void addImage(ImageCreateDto dto) {
        Optional.of(dto)
                .map(d -> {
                    uploadImages(d.getImage());
                    return imageCreateMapper.map(d);
                })
                .map(imageRepository::save);
    }

    public Optional<byte[]> findImages(Integer imageId) {
        return imageRepository.findById(imageId)
                .map(Image::getPath)
                .flatMap(this::get);
    }

    @SneakyThrows
    private void uploadImages(MultipartFile image) {
        if (!image.isEmpty()) {
            upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path fullImagePath = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path fullImagePath = Path.of(bucket, imagePath);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }

    public List<Long> findImagesIdByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(u -> imageRepository.findAllByUserId(u).stream()
                        .map(Image::getId)
                        .toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
