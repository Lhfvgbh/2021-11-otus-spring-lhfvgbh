package ru.otus.springboothomework3.services;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@SpringBootTest
class QuizAnswerServiceTest {

    @Autowired
    private QuizAnswerService quizAnswerService;
    private Quiz quiz;
    private Student student;
    private Question questionCorrect;
    private Question questionIncorrect;

    private static final String ANSWER_CORRECT = "y";
    @MockBean
    private IOService myTestBean;

    @Mock
    QuizResult expectedResult;

    @BeforeEach
    public void setupMock() {
        when(myTestBean.readLine()).thenReturn(ANSWER_CORRECT);
        questionCorrect = new Question("Is it test?", "Y");
        questionIncorrect = new Question("Is it test?", "N");
        student = new Student("Ivan", "Ivanov");
        setUpQuiz(questionCorrect);

        expectedResult = mock(QuizResult.class);
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
        when(expectedResult.getStatus()).thenReturn(QuizResult.Status.PASS);
        when(expectedResult.getScore()).thenReturn(6);

        expectedResult.provideResultData(student, 5);
        QuizResult actualResult = quizAnswerService.calculateAnswers(quiz, student);
        Assertions.assertEquals(expectedResult.getScore(), actualResult.getScore());
        Assertions.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }

    @Test
    @DisplayName("Check answer calculation for fail")
    void calculateAnswersFailTest() {
        setUpQuiz(questionIncorrect);

        when(expectedResult.getStatus()).thenReturn(QuizResult.Status.FAIL);
        when(expectedResult.getScore()).thenReturn(0);

        QuizResult actualResult = quizAnswerService.calculateAnswers(quiz, student);
        Assertions.assertEquals(expectedResult.getScore(), actualResult.getScore());
        Assertions.assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }
}