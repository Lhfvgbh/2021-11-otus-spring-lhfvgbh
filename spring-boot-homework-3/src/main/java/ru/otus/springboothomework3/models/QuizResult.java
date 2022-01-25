package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class QuizResult {
    private int score;
    private int questionCounter;
    private final Student student;
    private final int minScore;

    public QuizResult(Student student, int minScore) {
        this.student = student;
        this.minScore = minScore;
    }

    public void acceptAnswer(boolean answer) {
        questionCounter++;
        if (answer) {
            score++;
        }
    }

    public Status getStatus() {
        if (score > minScore) {
            return Status.PASS;
        } else {
            return Status.FAIL;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum Status {
        FAIL(false),
        PASS(true);

        private final boolean status;
    }


}
