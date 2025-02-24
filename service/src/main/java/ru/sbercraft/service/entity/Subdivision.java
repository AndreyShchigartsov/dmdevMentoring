package ru.sbercraft.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"users", "services"})
@EqualsAndHashCode(of = "subdivision")
@Entity
@Builder
public class Subdivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subdivision;

    @OneToMany(mappedBy = "subdivision")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "subdivision")
    private List<Service> services = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.setSubdivision(this);
    }

    public void addService(Service service) {
        services.add(service);
        service.setSubdivision(this);
    }
}