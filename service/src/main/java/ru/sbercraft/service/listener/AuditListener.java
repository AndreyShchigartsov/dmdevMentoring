package ru.sbercraft.service.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ru.sbercraft.service.entity.Event;

public class AuditListener {

    @PrePersist
    public void prePersist(Event event) {
        System.out.println();
    }

    @PreUpdate
    public void preUpdate(Event event) {
        System.out.println();
    }
}
