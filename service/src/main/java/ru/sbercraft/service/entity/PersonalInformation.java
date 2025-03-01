package ru.sbercraft.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"passportData", "birthCertificate"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@BatchSize(size = 5)
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportData;

    private String address;

    private String birthCertificate;

    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.setPersonalInformation(this);
    }
}
