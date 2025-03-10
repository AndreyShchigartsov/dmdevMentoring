package ru.sbercraft.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.sbercraft.service.entity.enums.Role;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//@NamedEntityGraph(
//        name = "WithImageAndStructureDivision",
//        attributeNodes = {
//                @NamedAttributeNode("images"),
//                @NamedAttributeNode("structureDivision")
//        }
//)
@Data
@ToString(exclude = {"images", "schedules"})
@EqualsAndHashCode(of = "login")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@DiscriminatorColumn(name = "type")
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StructureDivision structureDivision;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String login;

    private String password;

    private Instant registrationDate;

    private boolean active;

    private Role role;

    //Правильно понимаю что пару строк ниже можно удалить потому что когда
    // bidirectional и у дочерней сущности есть син ключ то не нужна свзяь?
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
