package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springboothomework3.models.Student;

import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class QuizServiceImplTest {
    @Autowired
    private QuizServiceImpl quizService;

    @MockBean
    private IOService myTestBean;

    @BeforeEach
    public void setupMock() {
        when(myTestBean.getStudentFirstName()).thenReturn(FIRSTNAME);
        when(myTestBean.getStudentLastName()).thenReturn(LASTNAME);
        when(myTestBean.getAnswer("")).thenReturn(ANSWER_CORRECT);
    }

    private static final String FIRSTNAME = "Ivan";
    private static final String LASTNAME = "Ivanov";
    private static final String ANSWER_CORRECT = "y";

    @Test
    @DisplayName("Check student profile creation")
    void studentCreationTest() {
        Student student = quizService.getStudentProfile();

        Assertions.assertEquals(FIRSTNAME, student.getFirstName());
        Assertions.assertEquals(LASTNAME, student.getLastName());

        log.info("success");
    }
}