package ru.otus.springboothomework3.services;

import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.exceptions.QuizException;
import ru.otus.springboothomework3.models.Quiz;

@Service
public interface QuestionService {
    Quiz getQuizQuestions() throws Exception;
}
