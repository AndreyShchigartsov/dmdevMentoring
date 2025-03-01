package ru.sbercraft.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import ru.sbercraft.service.entity.enums.Structure;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"id", "extraServices"})
@ToString(exclude = {"users", "extraServices", "rooms"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "structure_division")
//@BatchSize(size = 5)
public class StructureDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StructureDivision parentId;

    private Structure typeStructure;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<ExtraService> extraServices = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<Room> rooms = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
        user.setStructureDivision(this);
    }

    public void addExtraServices(ExtraService extraService) {
        this.extraServices.add(extraService);
        extraService.setStructureDivision(this);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setStructureDivision(this);
    }
}