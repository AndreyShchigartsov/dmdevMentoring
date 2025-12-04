package ru.vita.service.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String error) {
        super(error);
    }
}
