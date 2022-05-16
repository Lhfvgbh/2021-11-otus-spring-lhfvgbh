package ru.otus.homework_18.exception;

public class FallbackException extends RuntimeException {
    public FallbackException(String message) {
        super(message);
    }
}
