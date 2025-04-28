package ru.sbercraft.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.sbercraft.service.entity.enums.Role;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@NamedEntityGraph(
        name = "WithImageAndStructureDivision",
        attributeNodes = {
                @NamedAttributeNode("images"),
                @NamedAttributeNode("structureDivision")
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
@Table(name = "users")
@DiscriminatorColumn(name = "type")
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Room room;

    @ManyToOne(optional = false, fetch = LAZY)
    private StructureDivision structureDivision;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String username;

    private String password;

    private Instant registrationDate;

    private boolean active;

    private Role role;

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
