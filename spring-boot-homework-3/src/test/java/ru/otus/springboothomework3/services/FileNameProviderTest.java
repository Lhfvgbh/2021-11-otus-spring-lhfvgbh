package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"default.locale=ru-RU"})
class FileNameProviderTest {
    @Autowired
    private FilenameProviderImpl filenameProvider;

    @Test
    @DisplayName("Get correct filename according to RU locale")
    void getQuestionFileEnTest() {
        String phrase = "questions_ru.csv";
        Assertions.assertEquals(phrase, filenameProvider.getFilename());
        log.info("Success for the locale " + Locale.getDefault());
    }
}