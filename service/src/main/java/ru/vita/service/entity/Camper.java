package ru.vita.service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("Camper")
public class Camper extends User {

    //Дата прибытия
    private Instant arrivalDate;

    //Дата выезда
    private Instant departureDate;

    //Организация
    private String organization;
}
