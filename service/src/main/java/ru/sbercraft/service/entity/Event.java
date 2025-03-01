package ru.sbercraft.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.sbercraft.service.entity.enums.CategoryEvent;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "EventSchedules",
        attributeNodes = {
                @NamedAttributeNode("schedules")
        }
)
@NamedEntityGraph(
        name = "EventSchedulesAndUser",
        attributeNodes = {
                @NamedAttributeNode(value = "schedules", subgraph = "users")
        },
        subgraphs = @NamedSubgraph(name = "users", attributeNodes = @NamedAttributeNode("user"))
)
@Data
@ToString(exclude = "schedules")
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEvent category;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Schedule> schedules = new ArrayList<>();

    public void addSchedules(Schedule schedules) {
        this.schedules.add(schedules);
        schedules.setEvent(this);
    }
}