package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Question {

    private String question;
    private String correctAnswer;

}
