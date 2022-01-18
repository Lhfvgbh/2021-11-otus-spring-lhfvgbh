package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.ResultTotal;
import ru.otus.springboothomework3.models.Student;


@SpringBootTest
@Slf4j
class ResultServiceTest {

    @Autowired
    private ResultService resultService;
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("Is it test?", "Y");
    }

    @Test
    @DisplayName("Check answer validation")
    void checkAnswerTest() {
        boolean actualResult = resultService.checkAnswer(question, "y");
        Assertions.assertTrue(actualResult);
    }

    @Test
    @DisplayName("Check correct result status")
    void calculateTotalResultTest() {
        Student student = new Student("Ivan", "Ivanov");
        for (int i = 0; i < 6; i++) {
            resultService.checkAnswer(question, "y");
        }
        ResultTotal actualResult = resultService.calculateTotalResult(student);

        Assertions.assertEquals(7, actualResult.getScore());
        Assertions.assertEquals(ResultTotal.Status.PASS, actualResult.getStatus());
    }
}