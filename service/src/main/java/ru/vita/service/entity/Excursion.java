package ru.vita.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "service")
@ToString(of = "service")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StructureDivision structureDivision;

    @Column(unique = true)
    private String service;

    private Integer price;

    private Long duration;

    @OneToMany(mappedBy = "excursion")
    private List<UserExcursion> userExcursions = new ArrayList<>();

    public void setStructureDivision(StructureDivision structureDivision) {
        this.structureDivision = structureDivision;
        structureDivision.getExcursions().add(this);
    }

    public void addUserExcursion(UserExcursion userExcursion) {
        this.userExcursions.add(userExcursion);
        userExcursion.setExcursion(this);
    }
}