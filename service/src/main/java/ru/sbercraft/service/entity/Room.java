package ru.sbercraft.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(of = {"roomNumber","structureDivision"})
@EqualsAndHashCode(of = {"roomNumber","structureDivision"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roomNumber;

    private Integer seatsValue;

    @JoinColumn(name = "structure_division_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private StructureDivision structureDivision;

    @Builder.Default
    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    public void setUser(User user) {
        this.users.add(user);
        user.setRoom(this);
    }

    public void setStructureDivision(StructureDivision structureDivision) {
        this.structureDivision = structureDivision;
        structureDivision.getRooms().add(this);
    }
}
