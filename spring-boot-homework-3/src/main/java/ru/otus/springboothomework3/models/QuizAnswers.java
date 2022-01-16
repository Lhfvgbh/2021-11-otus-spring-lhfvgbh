package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class QuizAnswers {
    private List<Question> answers;
}
