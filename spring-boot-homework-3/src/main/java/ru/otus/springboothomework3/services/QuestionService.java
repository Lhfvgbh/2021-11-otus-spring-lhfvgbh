package ru.otus.springboothomework3.services;

import ru.otus.springboothomework3.exceptions.QuizException;
import ru.otus.springboothomework3.models.Quiz;

public interface QuestionService {
    Quiz buildQuiz() throws QuizException;
}
