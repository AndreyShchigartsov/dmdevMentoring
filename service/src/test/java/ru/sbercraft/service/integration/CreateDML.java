package ru.sbercraft.service.integration;

import jakarta.persistence.EntityManager;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sbercraft.service.entity.Camper;
import ru.sbercraft.service.entity.Event;
import ru.sbercraft.service.entity.ExtraService;
import ru.sbercraft.service.entity.Image;
import ru.sbercraft.service.entity.PersonalInformation;
import ru.sbercraft.service.entity.Room;
import ru.sbercraft.service.entity.Schedule;
import ru.sbercraft.service.entity.StructureDivision;
import ru.sbercraft.service.entity.User;
import ru.sbercraft.service.entity.Worker;
import ru.sbercraft.service.entity.enums.CategoryEvent;
import ru.sbercraft.service.entity.enums.JobPosition;
import ru.sbercraft.service.entity.enums.Role;
import ru.sbercraft.service.entity.enums.Status;
import ru.sbercraft.service.entity.enums.Structure;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

@UtilityClass
public class CreateDML {

    public void createData(EntityManager entityManager) {
        Event event1 = saveEvent(entityManager, "Баскетбол", CategoryEvent.SPORT);
        Event event2 = saveEvent(entityManager, "Волейбол", CategoryEvent.SPORT);
        Event event3 = saveEvent(entityManager, "Легкая атлетика", CategoryEvent.SPORT);
        Event event4 = saveEvent(entityManager, "Java", CategoryEvent.SCIENCE);
        Event event5 = saveEvent(entityManager, "Dmdev", CategoryEvent.SCIENCE);
        Event event6 = saveEvent(entityManager, "Spring", CategoryEvent.SCIENCE);

        StructureDivision structureDivisionVita = saveStructureDivision(entityManager, Structure.ORGANIZATIONAL, "Вита");
        StructureDivision structureDivisionAvrora = saveStructureDivision(entityManager, Structure.ORGANIZATIONAL, "Аврора");
        StructureDivision structureDivisionExcursionVita = saveStructureDivision(entityManager, Structure.ORGANIZATIONAL, "Экскурсвита");

        StructureDivision structureDivisionOrange = saveStructureDivision(entityManager, structureDivisionAvrora, Structure.PROVINCE, "Оранжевая губерния");
        StructureDivision structureDivisionBlue = saveStructureDivision(entityManager, structureDivisionAvrora, Structure.PROVINCE, "Синяя губерния");
        StructureDivision structureDivisionGrey = saveStructureDivision(entityManager, structureDivisionAvrora, Structure.PROVINCE, "Зеленая Губерния");
        StructureDivision structureDivisionNorth = saveStructureDivision(entityManager, structureDivisionVita, Structure.PROVINCE, "Губерния север");
        StructureDivision structureDivisionMiddle = saveStructureDivision(entityManager, structureDivisionVita, Structure.PROVINCE, "Губерния центр");
        StructureDivision structureDivisionSun = saveStructureDivision(entityManager, structureDivisionVita, Structure.PROVINCE, "Губерния солнце");

        StructureDivision structureDivision41 = saveStructureDivision(entityManager, structureDivisionOrange, Structure.GROUP, "41");
        StructureDivision structureDivision42 = saveStructureDivision(entityManager, structureDivisionOrange, Structure.GROUP, "42");
        StructureDivision structureDivision43 = saveStructureDivision(entityManager, structureDivisionOrange, Structure.GROUP, "43");
        StructureDivision structureDivision51 = saveStructureDivision(entityManager, structureDivisionBlue, Structure.GROUP, "51");
        StructureDivision structureDivision52 = saveStructureDivision(entityManager, structureDivisionBlue, Structure.GROUP, "52");
        StructureDivision structureDivision61 = saveStructureDivision(entityManager, structureDivisionGrey, Structure.GROUP, "61");
        StructureDivision structureDivision62 = saveStructureDivision(entityManager, structureDivisionGrey, Structure.GROUP, "62");
        StructureDivision structureDivision71 = saveStructureDivision(entityManager, structureDivisionNorth, Structure.GROUP, "71");
        StructureDivision structureDivision81 = saveStructureDivision(entityManager, structureDivisionMiddle, Structure.GROUP, "81");
        StructureDivision structureDivision82 = saveStructureDivision(entityManager, structureDivisionMiddle, Structure.GROUP, "82");
        StructureDivision structureDivision83 = saveStructureDivision(entityManager, structureDivisionMiddle, Structure.GROUP, "83");
        StructureDivision structureDivision91 = saveStructureDivision(entityManager, structureDivisionSun, Structure.GROUP, "91");
        StructureDivision structureDivision92 = saveStructureDivision(entityManager, structureDivisionSun, Structure.GROUP, "92");

        saveExtraServices(entityManager, structureDivisionExcursionVita, "Шоколадная фабрика", 1200, Duration.ofMinutes(150));
        saveExtraServices(entityManager, structureDivisionExcursionVita, "Sup-доски", 500, Duration.ofMinutes(30));
        saveExtraServices(entityManager, structureDivisionExcursionVita, "Сахарная вата", 300, Duration.ofMinutes(30));
        saveExtraServices(entityManager, structureDivisionExcursionVita, "Аквапарк", 2500, Duration.ofMinutes(300));

        Room room1 = saveRoom(entityManager, 101, 4, structureDivision41);
        Room room2 = saveRoom(entityManager, 111, 6, structureDivision52);
        Room room3 = saveRoom(entityManager, 112, 4, structureDivision71);
        Room room4 = saveRoom(entityManager, 101, 4, structureDivision81);
        Room room5 = saveRoom(entityManager, 101, 4, structureDivision81);
        Room room6 = saveRoom(entityManager, 101, 4, structureDivision91);
        Room room7 = saveRoom(entityManager, 101, 4, structureDivision92);
        Room room8 = saveRoom(entityManager, 101, 4, structureDivision92);

        Worker worker1 = saveWorker(entityManager, room1, "Deniz Matveenko", structureDivisionAvrora, Role.USER, JobPosition.ASSISTANT);
        Worker worker2 = saveWorker(entityManager, room2, "Andrey", structureDivisionAvrora, Role.USER, JobPosition.ASSISTANT);
        Worker worker3 = saveWorker(entityManager, room2, "Slava", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker4 = saveWorker(entityManager, room1, "Petya", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker5 = saveWorker(entityManager, room4, "Ivan", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker6 = saveWorker(entityManager, room4, "Sergey", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker7 = saveWorker(entityManager, room3, "Roman", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker8 = saveWorker(entityManager, room3, "Danil", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);

        Camper camper1 = saveCamper(entityManager, room5, "Sergey1", structureDivisionVita, Role.USER);
        Camper camper2 = saveCamper(entityManager, room5, "Anton", structureDivisionVita, Role.USER);
        Camper camper3 = saveCamper(entityManager, room5, "Alina", structureDivisionVita, Role.USER);
        Camper camper4 = saveCamper(entityManager, room6, "Katya", structureDivisionVita, Role.USER);
        Camper camper5 = saveCamper(entityManager, room7, "Slavyan", structureDivisionVita, Role.USER);
        Camper camper6 = saveCamper(entityManager, room8, "Halo", structureDivisionAvrora, Role.USER);
        Camper camper7 = saveCamper(entityManager, room8, "Matvey", structureDivisionAvrora, Role.USER);
        Camper camper8 = saveCamper(entityManager, room8, "Kiril", structureDivisionAvrora, Role.USER);

        saveImage(entityManager, "/path/21/sergey", camper1);
        saveImage(entityManager, "/path/21/anton", camper1);
        saveImage(entityManager, "/path/21/alina", camper1);
        saveImage(entityManager, "/path/21/katya", camper1);
        saveImage(entityManager, "/path/21/halo1", camper1);
        saveImage(entityManager, "/path/21/halo2", camper2);
        saveImage(entityManager, "/path/21/halo3", camper2);
        saveImage(entityManager, "/path/21/halo4", camper4);
        saveImage(entityManager, "/path/21/halo5", camper3);
        saveImage(entityManager, "/path/21/halo6", camper4);
        saveImage(entityManager, "/path/21/halo7", worker4);
        saveImage(entityManager, "/path/21/halo8", worker4);
        saveImage(entityManager, "/path/21/halo9", worker5);
        saveImage(entityManager, "/path/21/halo08", worker5);
        saveImage(entityManager, "/path/21/halo0", worker6);
        saveImage(entityManager, "/path/21/halo4", worker6);
        saveImage(entityManager, "/path/21/halo", worker7);
        saveImage(entityManager, "/path/21/halo", worker8);

        saveScheduleUser(entityManager, camper1, event1);
        saveScheduleUser(entityManager, camper1, event2);
        saveScheduleUser(entityManager, camper1, event3);
        saveScheduleUser(entityManager, camper1, event4);
        saveScheduleUser(entityManager, camper1, event5);
        saveScheduleUser(entityManager, camper2, event2);
        saveScheduleUser(entityManager, worker1, event3);
        saveScheduleUser(entityManager, worker2, event1);
        saveScheduleUser(entityManager, worker3, event2);
        saveScheduleGroup(entityManager, structureDivision42, event4);
        saveScheduleGroup(entityManager, structureDivision43, event5);
        saveScheduleGroup(entityManager, structureDivision71, event5);
        saveScheduleGroup(entityManager, structureDivisionExcursionVita, event6);
        saveScheduleGroup(entityManager, structureDivisionAvrora, event3);

        personalInfo(entityManager, worker1);
        personalInfo(entityManager, worker2);
        personalInfo(entityManager, camper1);
    }

    private void personalInfo(EntityManager session, User user) {
        PersonalInformation personalInformation = PersonalInformation.builder()
                .passportData("123123 1234")
                .user(user)
                .address("moskva/asdf")
                .birthCertificate("asdafaf")
                .birthDate(LocalDate.now())
                .build();
        session.persist(personalInformation);
    }

    private void saveScheduleGroup(EntityManager session, StructureDivision structureDivision, Event event) {
        Schedule schedule = Schedule.builder()
                .structureDivision(structureDivision)
                .event(event)
                .dateTime(Instant.now())
                .status(Status.READY)
                .build();
        session.persist(schedule);
    }

    private void saveScheduleUser(EntityManager session, User user, Event event) {
        Schedule schedule = Schedule.builder()
                .user(user)
                .event(event)
                .dateTime(Instant.now())
                .status(Status.READY)
                .build();
        session.persist(schedule);
    }

    private void saveImage(EntityManager session, String path, User user) {
        Image image = Image.builder()
                .path(path)
                .user(user)
                .build();
        session.persist(image);
    }

    private Camper saveCamper(EntityManager session, Room room, String login, StructureDivision structure, Role role) {
        Camper camper = Camper.builder()
                .room(room)
                .login(login)
                .lastname("lastname")
                .password("pass")
                .registrationDate(Instant.now())
                .structureDivision(structure)
                .role(role)
                .active(true)
                .checkInDate(Instant.now())
                .departureDate(Instant.now())
                .build();
        session.persist(camper);
        return camper;
    }

    private Worker saveWorker(EntityManager session, Room room, String login, StructureDivision structure, Role role, JobPosition jobPosition) {
        Worker worker = Worker.builder()
                .room(room)
                .login(login)
                .lastname("lastname")
                .password("pass")
                .registrationDate(Instant.now())
                .structureDivision(structure)
                .role(role)
                .salary(25000)
                .jobPosition(jobPosition)
                .active(true)
                .build();
        session.persist(worker);
        return worker;
    }

    private Room saveRoom(EntityManager session, Integer roomNumber, Integer seatsValue, StructureDivision structureDivision) {
        Room room = Room.builder()
                .roomNumber(roomNumber)
                .seatsValue(seatsValue)
                .structureDivision(structureDivision)
                .build();
        session.persist(room);
        return room;
    }

    private ExtraService saveExtraServices(EntityManager session, StructureDivision structureDivision, String name, Integer price, Duration duration) {
        ExtraService extraService = ExtraService.builder()
                .structureDivision(structureDivision)
                .name(name)
                .price(price)
                .duration(duration)
                .build();
        session.persist(extraService);
        return extraService;
    }

    private StructureDivision saveStructureDivision(EntityManager session, Structure structure, String name) {
        StructureDivision structureDivision = StructureDivision.builder()
                .typeStructure(structure)
                .name(name)
                .build();
        session.persist(structureDivision);
        return structureDivision;
    }

    private StructureDivision saveStructureDivision(EntityManager session, StructureDivision structureDivisionParent, Structure structure, String name) {
        StructureDivision structureDivision = StructureDivision.builder()
                .parent(structureDivisionParent)
                .typeStructure(structure)
                .name(name)
                .build();
        session.persist(structureDivision);
        return structureDivision;
    }

    private Event saveEvent(EntityManager session, String name, CategoryEvent category) {
        Event event = Event.builder()
                .name(name)
                .category(category)
                .build();
        session.persist(event);
        return event;
    }
}