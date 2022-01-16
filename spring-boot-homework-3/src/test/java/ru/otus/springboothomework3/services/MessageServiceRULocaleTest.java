package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"default.locale=ru-RU"})
class MessageServiceRULocaleTest {
    @Autowired
    MessageService messageService;

    @Test
    @DisplayName("Get correct file according to RU locale")
    void getQuestionFileRuTest() {
        String filename = "questions_ru.csv";
        Assertions.assertEquals(filename, messageService.getQuestionFile());
        log.info("Success for the locale " + Locale.getDefault());
    }
}