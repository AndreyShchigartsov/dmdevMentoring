package ru.sbercraft.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@Data
@EqualsAndHashCode(of = "service")
@ToString(of = "service")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ExtraService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StructureDivision structureDivision;

    @Column(unique = true)
    private String service;

    private Integer price;

    private Long duration;

    public void setStructureDivision(StructureDivision structureDivision) {
        this.structureDivision = structureDivision;
        structureDivision.getExtraServices().add(this);
    }
}