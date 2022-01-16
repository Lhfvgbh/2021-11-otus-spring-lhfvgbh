package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

//@Service
@Slf4j
public class LanguageService {

    //todo:in message service
    /*private MessageService(@Autowired MessageSource msg) {
        this.locale = LanguageService.getLocale();
        this.msg = msg;
    }*/

    @Getter
    private static Locale locale;
    private static String language;

    public LanguageService() {
        setLocale(language);
    }

    public void setLocale(String language) {
        if (language.equalsIgnoreCase("ru")) {
            locale = Locale.forLanguageTag("ru-RU");
            log.info("Выбранная локаль: ru");
        } else {
            locale = Locale.forLanguageTag("");
            log.info("Default locale: en");
        }
        LocaleContextHolder.setLocale(locale);
    }

    public static void readLocale() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please chose the language of the quiz (en or ru)\nПожалуйста, выберите язык тестирования (en or ru)");
        language = scanner.nextLine();
    }

}