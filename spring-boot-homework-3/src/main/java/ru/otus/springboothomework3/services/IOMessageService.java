package ru.otus.springboothomework3.services;

public interface IOMessageService {
    void printLine(String line, Object... args);

    String readLine();
}
