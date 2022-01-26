package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.springboothomework3.models.Quiz;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Slf4j
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

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
    void readQuestionsSizeTest() throws Exception{
        Quiz testQuiz = questionService.buildQuiz();
        Assertions.assertEquals(INPUT_FILE_SIZE, testQuiz.getNumberOfQuestions());
    }
}