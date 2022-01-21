package ru.otus.springboothomework3.exceptions;

public class QuizException extends Exception {
    public QuizException(String message) {
        super(message);
    }

    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
