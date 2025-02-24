package ru.sbercraft.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "subdivision")
@EqualsAndHashCode(of = "email")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Subdivision subdivision;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Room room;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private LocalDateTime dateRegistration;

    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Image> images;

    @OneToOne(mappedBy = "user")
    private PersonalInformation personalInformation;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;

    public void addImage(Image image) {
        images.add(image);
        image.setUser(this);
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setUser(this);
    }
}
