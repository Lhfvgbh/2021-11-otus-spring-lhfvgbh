package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"default.language=ru"})
class FilenameProviderTest {
    @Autowired
    private FilenameProviderImpl filenameProvider;
    @Value("${default.language}")
    private String language;

    @Test
    @DisplayName("Get correct filename according to locale")
    void getQuestionFileEnTest() {
        String phrase = "questions_ru.csv";
        Assertions.assertEquals(phrase, filenameProvider.getFilename());
        log.info("Success for the locale: " + language);
    }
}