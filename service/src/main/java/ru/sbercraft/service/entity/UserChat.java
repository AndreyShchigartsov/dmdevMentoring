package ru.sbercraft.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    private String message;

    private String dateMessage;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    private void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }

}
