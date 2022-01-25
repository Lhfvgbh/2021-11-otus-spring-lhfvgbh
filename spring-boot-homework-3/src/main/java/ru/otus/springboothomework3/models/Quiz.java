package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Quiz {
    private List<Question> questions;
    private int numberOfQuestions;

}
