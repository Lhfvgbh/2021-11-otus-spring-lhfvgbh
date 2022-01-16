package ru.otus.springboothomework3.services;

import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.*;

@Service
public interface ResultService {
    boolean checkAnswer(Question question, String answer);

    ResultTotal calculateTotalResult(Student student);
}
