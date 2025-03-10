package ru.sbercraft.service.util;

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

    public void createData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Event event1 = saveEvent(session, "Баскетбол", CategoryEvent.SPORT);
        Event event2 = saveEvent(session, "Волейбол", CategoryEvent.SPORT);
        Event event3 = saveEvent(session, "Легкая атлетика", CategoryEvent.SPORT);
        Event event4 = saveEvent(session, "Java", CategoryEvent.SCIENCE);
        Event event5 = saveEvent(session, "Dmdev", CategoryEvent.SCIENCE);
        Event event6 = saveEvent(session, "Spring", CategoryEvent.SCIENCE);

        StructureDivision structureDivisionVita = saveStructureDivision(session, Structure.ORGANIZATIONAL, "Вита");
        StructureDivision structureDivisionAvrora = saveStructureDivision(session, Structure.ORGANIZATIONAL, "Аврора");
        StructureDivision structureDivisionExcursionVita = saveStructureDivision(session, Structure.ORGANIZATIONAL, "Экскурсвита");

        StructureDivision structureDivisionOrange = saveStructureDivision(session, structureDivisionAvrora, Structure.PROVINCE, "Оранжевая губерния");
        StructureDivision structureDivisionBlue = saveStructureDivision(session, structureDivisionAvrora, Structure.PROVINCE, "Синяя губерния");
        StructureDivision structureDivisionGrey = saveStructureDivision(session, structureDivisionAvrora, Structure.PROVINCE, "Зеленая Губерния");
        StructureDivision structureDivisionNorth = saveStructureDivision(session, structureDivisionVita, Structure.PROVINCE, "Губерния север");
        StructureDivision structureDivisionMiddle = saveStructureDivision(session, structureDivisionVita, Structure.PROVINCE, "Губерния центр");
        StructureDivision structureDivisionSun = saveStructureDivision(session, structureDivisionVita, Structure.PROVINCE, "Губерния солнце");

        StructureDivision structureDivision41 = saveStructureDivision(session, structureDivisionOrange, Structure.GROUP, "41");
        StructureDivision structureDivision42 = saveStructureDivision(session, structureDivisionOrange, Structure.GROUP, "42");
        StructureDivision structureDivision43 = saveStructureDivision(session, structureDivisionOrange, Structure.GROUP, "43");
        StructureDivision structureDivision51 = saveStructureDivision(session, structureDivisionBlue, Structure.GROUP, "51");
        StructureDivision structureDivision52 = saveStructureDivision(session, structureDivisionBlue, Structure.GROUP, "52");
        StructureDivision structureDivision61 = saveStructureDivision(session, structureDivisionGrey, Structure.GROUP, "61");
        StructureDivision structureDivision62 = saveStructureDivision(session, structureDivisionGrey, Structure.GROUP, "62");
        StructureDivision structureDivision71 = saveStructureDivision(session, structureDivisionNorth, Structure.GROUP, "71");
        StructureDivision structureDivision81 = saveStructureDivision(session, structureDivisionMiddle, Structure.GROUP, "81");
        StructureDivision structureDivision82 = saveStructureDivision(session, structureDivisionMiddle, Structure.GROUP, "82");
        StructureDivision structureDivision83 = saveStructureDivision(session, structureDivisionMiddle, Structure.GROUP, "83");
        StructureDivision structureDivision91 = saveStructureDivision(session, structureDivisionSun, Structure.GROUP, "91");
        StructureDivision structureDivision92 = saveStructureDivision(session, structureDivisionSun, Structure.GROUP, "92");

        saveExtraServices(session, structureDivisionExcursionVita, "Шоколадная фабрика", 1200, Duration.ofMinutes(150));
        saveExtraServices(session, structureDivisionExcursionVita, "Sup-доски", 500, Duration.ofMinutes(30));
        saveExtraServices(session, structureDivisionExcursionVita, "Сахарная вата", 300, Duration.ofMinutes(30));
        saveExtraServices(session, structureDivisionExcursionVita, "Аквапарк", 2500, Duration.ofMinutes(300));

        Room room1 = saveRoom(session, 101, 4, structureDivision41);
        Room room2 = saveRoom(session, 111, 6, structureDivision52);
        Room room3 = saveRoom(session, 112, 4, structureDivision71);
        Room room4 = saveRoom(session, 101, 4, structureDivision81);
        Room room5 = saveRoom(session, 101, 4, structureDivision81);
        Room room6 = saveRoom(session, 101, 4, structureDivision91);
        Room room7 = saveRoom(session, 101, 4, structureDivision92);
        Room room8 = saveRoom(session, 101, 4, structureDivision92);

        Worker worker1 = saveWorker(session, room1, "Deniz Matveenko", structureDivisionAvrora, Role.USER, JobPosition.ASSISTANT);
        Worker worker2 = saveWorker(session, room2, "Andrey", structureDivisionAvrora, Role.USER, JobPosition.ASSISTANT);
        Worker worker3 = saveWorker(session, room2, "Slava", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker4 = saveWorker(session, room1, "Petya", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker5 = saveWorker(session, room4, "Ivan", structureDivisionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker6 = saveWorker(session, room4, "Sergey", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker7 = saveWorker(session, room3, "Roman", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);
        Worker worker8 = saveWorker(session, room3, "Danil", structureDivisionExcursionVita, Role.USER, JobPosition.ASSISTANT);

        Camper camper1 = saveCamper(session, room5, "Sergey1", structureDivisionVita, Role.USER);
        Camper camper2 = saveCamper(session, room5, "Anton", structureDivisionVita, Role.USER);
        Camper camper3 = saveCamper(session, room5, "Alina", structureDivisionVita, Role.USER);
        Camper camper4 = saveCamper(session, room6, "Katya", structureDivisionVita, Role.USER);
        Camper camper5 = saveCamper(session, room7, "Slavyan", structureDivisionVita, Role.USER);
        Camper camper6 = saveCamper(session, room8, "Halo", structureDivisionAvrora, Role.USER);
        Camper camper7 = saveCamper(session, room8, "Matvey", structureDivisionAvrora, Role.USER);
        Camper camper8 = saveCamper(session, room8, "Kiril", structureDivisionAvrora, Role.USER);

        saveImage(session, "/path/21/sergey", camper1);
        saveImage(session, "/path/21/anton", camper1);
        saveImage(session, "/path/21/alina", camper1);
        saveImage(session, "/path/21/katya", camper1);
        saveImage(session, "/path/21/halo1", camper1);
        saveImage(session, "/path/21/halo2", camper2);
        saveImage(session, "/path/21/halo3", camper2);
        saveImage(session, "/path/21/halo4", camper4);
        saveImage(session, "/path/21/halo5", camper3);
        saveImage(session, "/path/21/halo6", camper4);
        saveImage(session, "/path/21/halo7", worker4);
        saveImage(session, "/path/21/halo8", worker4);
        saveImage(session, "/path/21/halo9", worker5);
        saveImage(session, "/path/21/halo08", worker5);
        saveImage(session, "/path/21/halo0", worker6);
        saveImage(session, "/path/21/halo4", worker6);
        saveImage(session, "/path/21/halo", worker7);
        saveImage(session, "/path/21/halo", worker8);

        saveScheduleUser(session, camper1, event1);
        saveScheduleUser(session, camper1, event2);
        saveScheduleUser(session, camper1, event3);
        saveScheduleUser(session, camper1, event4);
        saveScheduleUser(session, camper1, event5);
        saveScheduleUser(session, camper2, event2);
        saveScheduleUser(session, worker1, event3);
        saveScheduleUser(session, worker2, event1);
        saveScheduleUser(session, worker3, event2);
        saveScheduleGroup(session, structureDivision42, event4);
        saveScheduleGroup(session, structureDivision43, event5);
        saveScheduleGroup(session, structureDivision71, event5);
        saveScheduleGroup(session, structureDivisionExcursionVita, event6);
        saveScheduleGroup(session, structureDivisionAvrora, event3);

        personalInfo(session, worker1);
        personalInfo(session, worker2);
        personalInfo(session, camper1);

        session.getTransaction().commit();
    }

    private void personalInfo(Session session, User user) {
        PersonalInformation personalInformation = PersonalInformation.builder()
                .passportData("123123 1234")
                .user(user)
                .address("moskva/asdf")
                .birthCertificate("asdafaf")
                .birthDate(LocalDate.now())
                .build();
        session.persist(personalInformation);
    }

    private void saveScheduleGroup(Session session, StructureDivision structureDivision, Event event) {
        Schedule schedule = Schedule.builder()
                .structureDivision(structureDivision)
                .event(event)
                .dateTime(Instant.now())
                .status(Status.READY)
                .build();
        session.persist(schedule);
    }

    private void saveScheduleUser(Session session, User user, Event event) {
        Schedule schedule = Schedule.builder()
                .user(user)
                .event(event)
                .dateTime(Instant.now())
                .status(Status.READY)
                .build();
        session.persist(schedule);
    }

    private void saveImage(Session session, String path, User user) {
        Image image = Image.builder()
                .path(path)
                .user(user)
                .build();
        session.persist(image);
    }

    private Camper saveCamper(Session session, Room room, String login, StructureDivision structure, Role role) {
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

    private Worker saveWorker(Session session, Room room, String login, StructureDivision structure, Role role, JobPosition jobPosition) {
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

    private Room saveRoom(Session session, Integer roomNumber, Integer seatsValue, StructureDivision structureDivision) {
        Room room = Room.builder()
                .roomNumber(roomNumber)
                .seatsValue(seatsValue)
                .structureDivision(structureDivision)
                .build();
        session.persist(room);
        return room;
    }

    private ExtraService saveExtraServices(Session session, StructureDivision structureDivision, String name, Integer price, Duration duration) {
        ExtraService extraService = ExtraService.builder()
                .structureDivision(structureDivision)
                .name(name)
                .price(price)
                .duration(duration)
                .build();
        session.persist(extraService);
        return extraService;
    }

    private StructureDivision saveStructureDivision(Session session, Structure structure, String name) {
        StructureDivision structureDivision = StructureDivision.builder()
                .typeStructure(structure)
                .name(name)
                .build();
        session.persist(structureDivision);
        return structureDivision;
    }

    private StructureDivision saveStructureDivision(Session session, StructureDivision structureDivisionParent, Structure structure, String name) {
        StructureDivision structureDivision = StructureDivision.builder()
                .parent(structureDivisionParent)
                .typeStructure(structure)
                .name(name)
                .build();
        session.persist(structureDivision);
        return structureDivision;
    }

    private Event saveEvent(Session session, String name, CategoryEvent category) {
        Event event = Event.builder()
                .name(name)
                .category(category)
                .build();
        session.persist(event);
        return event;
    }
}