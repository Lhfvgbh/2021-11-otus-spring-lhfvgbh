package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResultTotal {
    private int score;
    private Student student;
    private Status status;

    @AllArgsConstructor
    @Getter
    public enum Status {
        FAIL(false),
        PASS(true);

        private boolean status;
    }


}
