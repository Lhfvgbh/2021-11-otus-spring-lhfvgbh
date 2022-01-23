package ru.otus.springboothomework3.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Locale;

import static org.mockito.Mockito.when;

@SpringBootTest
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

    @BeforeEach
    void setupLocale(){
        setupLocale("");
    }

    @Test
    @DisplayName("Get the message by language")
    void getMessageRUTest() {
        setupLocale("ru");
        String actualResult = messageService.getMessage("message.welcome");
        String expectedResult = "Добро пожаловать в тестирование!";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get the message by default language")
    void getMessageDefaultTest() {
        setupLocale("en");
        String actualResult = messageService.getMessage("message.welcome");
        String expectedResult = "Welcome to the Quiz!";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get the message by default language")
    void getMessageENTest() {
        String actualResult = messageService.getMessage("message.welcome");
        String expectedResult = "Welcome to the Quiz!";
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
