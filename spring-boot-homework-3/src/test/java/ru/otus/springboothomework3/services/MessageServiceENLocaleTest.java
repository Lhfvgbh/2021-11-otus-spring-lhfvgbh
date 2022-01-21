package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"default.language=en"})
public class MessageServiceENLocaleTest {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    @DisplayName("Get the message by default language")
    void getMessageTest() {
        String actualResult = messageService.getMessage("message.welcome");
        String expectedResult = "Welcome to the Quiz!";
        Assertions.assertEquals(expectedResult, actualResult);
        log.info("Success");
    }
}
