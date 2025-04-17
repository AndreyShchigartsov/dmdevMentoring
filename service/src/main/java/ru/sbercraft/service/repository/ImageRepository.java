package ru.sbercraft.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}