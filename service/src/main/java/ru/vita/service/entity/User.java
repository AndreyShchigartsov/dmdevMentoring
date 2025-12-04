package ru.vita.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.vita.service.entity.enums.Role;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;


@NamedEntityGraph(
        name = "UserSchedules",
        attributeNodes = {
                @NamedAttributeNode("schedules")
        }
)
@Data
@ToString(exclude = {"images", "schedules"})
@EqualsAndHashCode(of = "username")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Room room;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "structure_division_id")
    private StructureDivision structureDivision;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    private Instant registrationDate;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = LAZY)
    private PersonalInformation personalInformation;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    public void setStructureDivision(StructureDivision structureDivision) {
        this.structureDivision = structureDivision;
        structureDivision.getUsers().add(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        room.getUsers().add(this);
    }

    public void addImages(Image image) {
        images.add(image);
        image.setUser(this);
    }

    public void addSchedules(Schedule schedule) {
        schedules.add(schedule);
        schedule.setUser(this);
    }

    public void clearRoom() {
        if (this.room != null) {
            this.room.getUsers().remove(this);  // Удаляем пользователя из старой комнаты
            this.room = null;                   // Обнуляем ссылку
        }
    }
}
