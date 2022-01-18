package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.ResultTotal;

import java.io.PrintStream;
import java.util.Scanner;

@Slf4j
@Service
public class IOService {

    private final LanguageService languageService;

    private final PrintStream outputStream;

    private final Scanner scanner;

    @Autowired
    public IOService(LanguageService languageService) {
        this.languageService = languageService;
        this.outputStream = System.out;

        scanner = new Scanner(System.in);
    }

    public String getStudentFirstName() {
        outputStream.println(languageService.getWelcomeMessage());
        outputStream.println(languageService.getFirstName());
        return scanner.nextLine();
    }

    public String getStudentLastName() {
        outputStream.println(languageService.getLastName());
        String lastname = scanner.nextLine();
        outputStream.println(languageService.getStartMessage());
        return lastname;
    }

    public void summarizeResult(ResultTotal result) {
        outputStream.println(languageService.getResult() + result.getStatus());
        outputStream.println(languageService.getScore() + result.getScore() + "/" + result.getQuestionCounter());
    }

    public String getAnswer(String question) {
        outputStream.println(question);
        return scanner.nextLine();
    }

    public void checkAnswer(boolean result) {
        outputStream.println(result);
    }

}
