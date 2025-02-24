package ru.sbercraft.service.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserChat {

    private User user;

    private Chat chat;

    private String message;

    private String dateMessage;
}
