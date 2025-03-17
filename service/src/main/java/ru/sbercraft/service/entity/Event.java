package ru.sbercraft.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.listener.AuditListener;

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
@ToString(of = "name")
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(value = AuditListener.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEvent category;

    @Builder.Default
    @OneToMany(mappedBy = "event")
    private List<Schedule> schedules = new ArrayList<>();

    public void addSchedules(Schedule schedules) {
        this.schedules.add(schedules);
        schedules.setEvent(this);
    }
}