package ru.otus.springboothomework3.services;

import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.*;

@Service
public class ResultServiceImpl implements ResultService {

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getCorrectAnswer().equalsIgnoreCase(answer);
    }
}
