package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class ResultServiceTest {

    @Autowired
    private ResultService resultService;
    private Quiz quiz;
    private Student student;
    private Question questionCorrect;
    private Question questionIncorrect;

    private static final String ANSWER_CORRECT = "y";
    @MockBean
    private IOService myTestBean;

    @BeforeEach
    public void setupMock() {
        when(myTestBean.readLine()).thenReturn(ANSWER_CORRECT);
        questionCorrect = new Question("Is it test?", "Y");
        questionIncorrect = new Question("Is it test?", "N");
        student = new Student("Ivan", "Ivanov");
        setUpQuiz(questionCorrect);
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

    @Test
    @DisplayName("Check answer calculation for pass")
    void calculateAnswersPassTest() {
        QuizResult expectedResult = new QuizResult();
        for (Question ignored : quiz.getQuestions()) {
            expectedResult.acceptAnswer(true);
        }
        expectedResult.calculateTotalResult(student, 5);
        QuizResult actualResult = resultService.calculateAnswers(quiz, student);
        Assertions.assertEquals(expectedResult.getScore(), actualResult.getScore());
        Assertions.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }

    @Test
    @DisplayName("Check answer calculation for fail")
    void calculateAnswersFailTest() {
        setUpQuiz(questionIncorrect);
        QuizResult expectedResult = new QuizResult();
        for (Question ignored : quiz.getQuestions()) {
            expectedResult.acceptAnswer(false);
        }
        expectedResult.calculateTotalResult(student, 5);
        QuizResult actualResult = resultService.calculateAnswers(quiz, student);
        Assertions.assertEquals(expectedResult.getScore(), actualResult.getScore());
        Assertions.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }
}