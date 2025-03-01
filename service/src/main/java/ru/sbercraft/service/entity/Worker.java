package ru.sbercraft.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.sbercraft.service.entity.enums.JobPosition;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("worker")
public class Worker extends User {

    private int salary;

    @Enumerated(EnumType.STRING)
    private JobPosition jobPosition;
}
