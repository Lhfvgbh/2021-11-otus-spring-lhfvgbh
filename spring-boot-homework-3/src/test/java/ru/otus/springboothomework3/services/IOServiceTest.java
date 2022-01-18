package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@SpringBootTest
@Slf4j
public class IOServiceTest {

    @Autowired
    private IOService ioService;

    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private static final String FIRSTNAME = "Ivan";
    private static final String LASTNAME = "Ivanov";
    private static final String ANSWER_CORRECT = "y";
    private static final String ANSWER_WRONG = "n";
    private static final String STATUS_CORRECT = "PASS";
    private static final String STATUS_WRONG = "FAIL";
    private static final int SCORE_CORRECT = 8;

    //@BeforeEach
    //void setSystemInput() {
    //    setSystemInput(ANSWER_CORRECT);
    //}

    @BeforeEach
    void setSystemOutput() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    void setSystemInput(String... args) {
        StringBuilder simulatedUserInput = new StringBuilder();
        for (String arg : args) {
            simulatedUserInput.append(arg)
                    .append(System.getProperty("line.separator"));
        }

        System.setIn(new ByteArrayInputStream(simulatedUserInput.toString().getBytes()));
    }

    @AfterAll
    static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    //@Test
    //@DisplayName("Check the answer input")
    //@Disabled("problem with the input")
    void getAnswerTest(){
        String question = "Is it a test?";
        String answer = "y";
        setSystemInput(answer);
        String answer2 = ioService.getAnswer(question);
        Assertions.assertEquals(question, System.out.toString());
        //Assertions.assertEquals(answer, answer2);
    }
}
