package ru.otus.springboothomework3.services;

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
    private final Locale locale;

    private MessageService(@Autowired MessageSource msg,
                           @Value("${default.locale}") String language) {
        this.locale = Locale.forLanguageTag(language);
        this.msg = msg;
    }

    public String getMessage(String key, Object... args) {
        return msg.getMessage(
                key,
                args,
                locale);
    }
}
