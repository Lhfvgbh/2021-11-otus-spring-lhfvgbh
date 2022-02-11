package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.TestPropertySource;
import ru.otus.springboothomework3.services.providers.FilenameProviderImpl;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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