package ru.otus.springboothomework3.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springboothomework3.models.*;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@SpringBootTest
class QuizAnswerServiceTest {

    @Autowired
    private QuizAnswerService quizAnswerService;
    private Quiz quiz;
    private Student student;

    private static final String ANSWER_CORRECT = "y";

    @MockBean
    private IOService ioService;

    @BeforeEach
    public void setup() {
        when(ioService.readLine()).thenReturn(ANSWER_CORRECT);
        student = new Student("Ivan", "Ivanov");
    }

    void setUpQuiz(Question question) {
        quiz = new Quiz(Arrays.asList(
                question,
                question,
                question,
                question,
                question,
                question
        ), 1);
    }

    @ParameterizedTest
    @DisplayName("Check answer calculation for fail")
    @CsvSource({"Y,6,PASS", "N,0,FAIL"})
    void calculateAnswersFailTest(String answer, int score, String s) {
        Question question = new Question("Is it test?", answer);
        setUpQuiz(question);

        QuizResult actualResult = quizAnswerService.calculateAnswers(quiz, student);
        Assertions.assertEquals(score, actualResult.getScore());
        Assertions.assertEquals(QuizResult.Status.valueOf(s), actualResult.getStatus());
    }
}