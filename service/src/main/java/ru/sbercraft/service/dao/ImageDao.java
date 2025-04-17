package ru.sbercraft.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercraft.service.entity.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {

}