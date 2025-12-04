package ru.vita.service.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.dto.image.ImageCreateDto;
import ru.vita.service.entity.Image;
import ru.vita.service.entity.User;
import ru.vita.service.exception.FileUploadException;
import ru.vita.service.mapper.create.ImageCreateMapper;
import ru.vita.service.repository.ImageRepository;
import ru.vita.service.repository.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:C:\\Users\\Andrey72\\Desktop\\work\\dmdevMentoring\\images}")
    private final String bucket;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    private final ImageCreateMapper createMapper;

    /**
     * Загружает изображение на сервер
     *
     * @param imageDto хранит в себе изображение и пользователя кому загружается изображение
     * @throws IllegalArgumentException если dto равен null
     */
    @Transactional
    public void addImage(ImageCreateDto imageDto) {
        Optional.ofNullable(imageDto)
                .map(d -> {
                    uploadImages(d.getImage());
                    return createMapper.map(d);
                })
                .map(imageRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("DTO для создания изображения не может быть пустым"));
    }

    /**
     * Возвращает изображения по imageId
     *
     * @param imageId идентификатор изображения
     * @return Optional с byte[], где byte[] - массив байт изображения
     */
    public Optional<byte[]> findImages(Integer imageId) {
        return Optional.ofNullable(imageId)
                .flatMap(imageRepository::findById)
                .map(Image::getPath)
                .flatMap(this::get);

    }

    /**
     * Возвращает идентификаторы изображений по userId
     *
     * @param userId идентификатор пользователя кому принадлежать изображения
     * @return List<Long> - где Long это идентификаторы изображений
     */
    public List<Long> findImagesIdByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(u -> imageRepository.findAllByUserId(u).stream()
                        .map(Image::getId)
                        .toList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void uploadImages(MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                upload(image.getOriginalFilename(), image.getInputStream());
            } catch (IOException e) {
                throw new FileUploadException("Не удалось загрузить файл: " + e.getMessage());
            }
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
}
