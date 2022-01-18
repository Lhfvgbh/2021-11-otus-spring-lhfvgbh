package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LanguageService {

    private final MessageService messageService;

    public LanguageService(@Autowired MessageService messageService) {
        this.messageService = messageService;
    }

    public String getQuestionFile() {
        return messageService.getMessage("file.questions");
    }

    public String getFileLanguage() {
        return messageService.getMessage("file.questions.locale");
    }

    public String getWelcomeMessage() {
        return messageService.getMessage("message.welcome");
    }

    public String getFirstName() {
        return messageService.getMessage("message.firstname");
    }

    public String getLastName() {
        return messageService.getMessage("message.lastname");
    }

    public String getStartMessage() {
        return messageService.getMessage("message.start");
    }

    public String getResult() {
        return messageService.getMessage("message.result");
    }

    public String getScore() {
        return messageService.getMessage("message.score");
    }
}