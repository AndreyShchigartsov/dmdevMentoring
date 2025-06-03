package ru.sbercraft.service.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum JobPosition {
    CAMP_DIRECTOR(10),          // Директор лагеря (высший приоритет, отвечает за всё)
    CAMP_ORGANIZATIONAL(8),     //Директор организации (высокий приоритет, отвечает за свою организацию)
    ASSOCIATE_ORGANIZATIONAL(7),// зам д иректор лагеря (высокий приоритет, ответсвенный за исполнение обязанностей)
    SENIOR_COUNSELOR(6),        // Старший вожатый (руководит вожатыми, отвечает за отряды)
    METHODOLOGIST_SPORT(6),     // Методист по спорту  (руководитель спортотряда)
    PROVINCE_MANAGER(3),        // Губернатор (Ответственный за свою губернию)
    COUNSELOR(2);               // Вожатый (работает с детьми, отвечает за свой отряд)

    private final int priorityLevel;

    public static Optional<JobPosition> find(String jobPosition) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(jobPosition))
                .findFirst();
    }
}
