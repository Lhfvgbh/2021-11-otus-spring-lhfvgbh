package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class MessageService {
    private final MessageSource msg;
    @Getter
    private final Locale locale;

    private MessageService(@Autowired MessageSource msg, @Value("${default.locale}") String language) {
        this.locale = Locale.forLanguageTag(language);
        this.msg = msg;
    }

    public String getQuestionFile() {
        return msg.getMessage(
                "file.questions",
                new String[]{},
                locale);
    }

    public String getWelcomeMessage() {
        return msg.getMessage(
                "message.welcome",
                new String[]{},
                locale);
    }

    public String getFirstName() {
        return msg.getMessage(
                "message.firstname",
                new String[]{},
                locale);
    }

    public String getLastName() {
        return msg.getMessage(
                "message.lastname",
                new String[]{},
                locale);
    }

    public String getStartMessage() {
        return msg.getMessage(
                "message.start",
                new String[]{},
                locale);
    }

    public String getResult() {
        return msg.getMessage(
                "message.result",
                new String[]{},
                locale);
    }

    public String getScore() {
        return msg.getMessage(
                "message.score",
                new String[]{},
                locale);
    }
}
