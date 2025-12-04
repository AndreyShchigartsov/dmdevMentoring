package ru.vita.service.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vita.service.entity.Image;
import ru.vita.service.entity.User;
import ru.vita.service.integration.IntegrationTestBase;
import ru.vita.service.repository.ImageRepository;
import ru.vita.service.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
class ImageRepositoryIT extends IntegrationTestBase  {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Test
    void checkThatByUserGetImages() {
        User user = userRepository.findById(1L).get();

        List<Image> images = imageRepository.findAllByUserId(user);

        Assertions.assertEquals(4, images.size());
    }
}