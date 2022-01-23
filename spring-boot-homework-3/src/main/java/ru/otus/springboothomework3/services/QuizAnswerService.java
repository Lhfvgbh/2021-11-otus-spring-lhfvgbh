package ru.otus.springboothomework3.services;

import ru.otus.springboothomework3.models.*;

public interface QuizAnswerService {
    QuizResult calculateAnswers(Quiz quiz, Student student);
}
