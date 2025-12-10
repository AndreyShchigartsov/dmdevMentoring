package ru.vita.service.exception;

public class StructureDivisionNotFoundException extends Exception {
    public StructureDivisionNotFoundException(Integer id) {
        super("Структура не найдена с ID: " + id);
    }
}
