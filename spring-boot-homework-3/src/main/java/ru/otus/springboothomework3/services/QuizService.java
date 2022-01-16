package ru.otus.springboothomework3.services;

import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.ResultTotal;

@Service
public interface QuizService {
    ResultTotal startQuiz();
}
