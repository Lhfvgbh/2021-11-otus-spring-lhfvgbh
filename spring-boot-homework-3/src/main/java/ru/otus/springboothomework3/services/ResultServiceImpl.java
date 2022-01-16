package ru.otus.springboothomework3.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.*;

@Service
public class ResultServiceImpl implements ResultService {

    private final int minScore;
    private int realScore;

    ResultServiceImpl(@Value("${throughput}") int minScore) {
        this.minScore = minScore;
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
            realScore++;
            return true;
        }
        return false;
    }

    @Override
    public ResultTotal calculateTotalResult(Student student) {
        ResultTotal resultTotal;
        if (realScore > minScore) {
            resultTotal = new ResultTotal(realScore, student, ResultTotal.Status.PASS);
        } else {
            resultTotal = new ResultTotal(realScore, student, ResultTotal.Status.FAIL);
        }
        realScore = 0;
        return resultTotal;
    }
}
