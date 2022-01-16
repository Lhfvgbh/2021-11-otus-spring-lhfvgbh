package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springboothomework3.models.Quiz;

@SpringBootTest
@Slf4j
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    private static final int INPUT_FILE_SIZE = 10;

    @BeforeAll
    static void beforeQuestionServiceImplTest() {
        log.info("The beginning of the testing QuestionService");
    }

    @AfterAll
    static void afterQuestionServiceImplTest() {
        log.info("The ending of the testing QuestionService");
    }

    @Test
    @DisplayName("Get the size of the question file")
    void readQuestionsSizeTest() {
        Quiz testQuiz = questionService.readQuestions();
        Assertions.assertEquals(INPUT_FILE_SIZE, testQuiz.getNumberOfQuestions());
        log.info("Success");
    }
}