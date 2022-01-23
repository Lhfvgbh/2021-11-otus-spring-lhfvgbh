package ru.otus.springboothomework3.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizResult {
    private int score;
    private Student student;
    private Status status;
    private int questionCounter;
    private int minScore;

    public void acceptAnswer(boolean answer) {
        questionCounter++;
        if (answer) {
            score++;
        }
    }

    public void provideResultData(Student student, int minScore) {
        this.student = student;
        this.minScore = minScore;
    }

    public Status getStatus() {
        if (score > minScore) {
            status = Status.PASS;
        } else {
            status = Status.FAIL;
        }
        return status;
    }

    @AllArgsConstructor
    @Getter
    public enum Status {
        FAIL(false),
        PASS(true);

        private final boolean status;
    }


}
