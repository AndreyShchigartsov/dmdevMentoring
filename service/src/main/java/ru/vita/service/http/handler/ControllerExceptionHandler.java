package ru.vita.service.http.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import ru.vita.service.exception.FileUploadException;

@Slf4j
@ControllerAdvice(basePackages = "ru.vita.service.http.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public String handleException(Exception exception, HttpServletRequest request) {
        log.error("Failed to return response", exception);
        return "error/error500";
    }

    @ExceptionHandler(FileUploadException.class)
    public String handleFileUpload(FileUploadException e) {
        log.error("Ошибка загрузки файла: {}", e.getMessage(), e);
        return "error/error500";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.error("Ошибка целостности данных БД", e);
        return "error/error500";
    }
}
