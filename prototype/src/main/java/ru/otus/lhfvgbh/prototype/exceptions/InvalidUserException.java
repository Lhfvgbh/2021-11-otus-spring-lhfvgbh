package ru.otus.lhfvgbh.prototype.exceptions;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }

    //"Cannot save sign up, please check your input!"
}
