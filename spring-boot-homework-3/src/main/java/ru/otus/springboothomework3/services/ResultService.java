package ru.otus.springboothomework3.services;

import ru.otus.springboothomework3.models.*;

public interface ResultService {
    //boolean checkAnswer(Question question, String answer);
    QuizResult calculateAnswers(Quiz quiz, Student student);
}
