package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springboothomework3.models.Question;


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
}