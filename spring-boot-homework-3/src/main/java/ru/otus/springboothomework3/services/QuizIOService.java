package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.QuizResult;

@Service
@Slf4j
public class QuizIOService {
    MessageService messageService;
    IOService ioService;

    public QuizIOService(@Autowired MessageService messageService,
                         @Autowired IOService ioService) {
        this.messageService = messageService;
        this.ioService = ioService;

    }

    public String getStudentFirstName() {
        ioService.printLine(messageService.getMessage("message.welcome"));
        ioService.printLine(messageService.getMessage("message.firstname"));
        return ioService.readLine();
    }

    public String getStudentLastName() {
        ioService.printLine(messageService.getMessage("message.lastname"));
        String lastname = ioService.readLine();
        ioService.printLine(messageService.getMessage("message.start"));
        return lastname;
    }

    public void summarizeResult(QuizResult result) {
        ioService.printLine(messageService.getMessage("message.result") + result.getStatus());
        ioService.printLine(messageService.getMessage("message.score") + result.getScore() + "/" + result.getQuestionCounter());
    }

    public String getAnswer(String question) {
        ioService.printLine(question);
        return ioService.readLine();
    }

    public void checkAnswer(boolean result) {
        ioService.printLine(String.valueOf(result));
    }

}
