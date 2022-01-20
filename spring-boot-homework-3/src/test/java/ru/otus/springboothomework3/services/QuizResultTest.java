package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;

@Slf4j
public class QuizResultTest {

    private static final int THROUGHPUT = 5;
    private static final boolean CORRECT_ANSWER = true;
    private static final boolean INCORRECT_ANSWER = false;

    @Test
    @DisplayName("Check correct result status")
    void calculateTotalResultTest() {
        Student student = new Student("Ivan", "Ivanov");
        QuizResult result = new QuizResult();

        result.acceptAnswer(INCORRECT_ANSWER);
        for (int i = 0; i < 6; i++) {
            result.acceptAnswer(CORRECT_ANSWER);
        }
        result.calculateTotalResult(student, THROUGHPUT);

        Assertions.assertEquals(6, result.getScore());
        Assertions.assertEquals(7, result.getQuestionCounter());
        Assertions.assertEquals(QuizResult.Status.PASS, result.getStatus());
        log.info("success");
    }
}
