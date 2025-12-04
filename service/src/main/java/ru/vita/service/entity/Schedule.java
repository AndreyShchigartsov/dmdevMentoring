package ru.vita.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vita.service.entity.enums.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "user_created")
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdUser;

    @JoinColumn(name = "structure_division_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StructureDivision structureDivision;

    @JoinColumn(name = "event_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Event event;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String action;

    public void setUser(User user) {
        this.user = user;
        user.getSchedules().add(this);
    }
}
