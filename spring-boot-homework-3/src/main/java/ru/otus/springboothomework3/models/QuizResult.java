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

    public void acceptAnswer(boolean answer) {
        questionCounter++;
        if (answer) {
            score++;
        }
    }

    public void calculateTotalResult(Student student, int minScore) {
        this.student = student;
        if (score > minScore) {
            status = Status.PASS;
        } else {
            status = Status.FAIL;
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
