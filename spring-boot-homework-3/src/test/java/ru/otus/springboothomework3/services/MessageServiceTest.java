package ru.otus.springboothomework3.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.springboothomework3.services.providers.FilenameProvider;
import ru.otus.springboothomework3.services.providers.LocaleProvider;

import java.util.Locale;

import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class MessageServiceTest {

    @Autowired
    private MessageServiceImpl messageService;

    @MockBean
    private LocaleProvider localeProvider;

    @MockBean
    private FilenameProvider filenameProvider;

    private void setupLocale(String language) {
        when(localeProvider.getLocale()).thenReturn(Locale.forLanguageTag(language));
    }

    @ParameterizedTest
    @DisplayName("Get the message by language")
    @CsvSource({"'',Welcome to the Quiz!", "en,Welcome to the Quiz!", "ru,Добро пожаловать в тестирование!"})
    void getMessageTest(String language, String expectedResult) {
        setupLocale(language);
        String actualResult = messageService.getMessage("message.welcome");
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
