package ru.sbercraft.service.entity.enums;

public enum JobPosition {
    CAMP_DIRECTOR(10),    // Директор лагеря (высший приоритет, отвечает за всё)
    SENIOR_COUNSELOR(8), // Старший вожатый (руководит вожатыми, отвечает за отряды)
    COUNSELOR(6),        // Вожатый (работает с детьми, отвечает за свой отряд)
    ASSISTANT(4),        // Помощник вожатого (помогает вожатому, меньшая ответственность)
    TRAINEE(2);          // Стажёр (новичок, учится и помогает вожатым)

    private final int priorityLevel;

    // Конструктор для установки приоритета
    JobPosition(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    // Геттер для получения уровня приоритета
    public int getPriorityLevel() {
        return priorityLevel;
    }
}
