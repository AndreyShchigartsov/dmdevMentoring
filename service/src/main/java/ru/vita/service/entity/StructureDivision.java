package ru.vita.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import ru.vita.service.entity.enums.Structure;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"schedules" , "users", "excursion", "rooms"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "structure_division")
public class StructureDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StructureDivision parent;

    @Enumerated(EnumType.STRING)
    private Structure typeStructure;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision", cascade = CascadeType.REMOVE)
    private List<Excursion> excursions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<Room> rooms = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "structureDivision")
    private List<Schedule> schedules = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
        user.setStructureDivision(this);
    }

    public void addExcursion(Excursion excursion) {
        this.excursions.add(excursion);
        excursion.setStructureDivision(this);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        room.setStructureDivision(this);
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setStructureDivision(this);
    }
}