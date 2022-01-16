package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"default.locale="})
class MessageServiceDefaultLocaleTest {
    @Autowired
    MessageService messageService;

    @Test
    @DisplayName("Get correct file according to default locale")
    void getQuestionFileEnTest() {

        String filename = "questions.csv";
        Assertions.assertEquals(filename, messageService.getQuestionFile());
        log.info("Success for the locale " + Locale.getDefault());
    }
}