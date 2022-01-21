package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{
    private final MessageSource msg;
    private final Locale locale;

    @Autowired
    private MessageServiceImpl(MessageSource msg,
                               @Value("${default.language}") String language) {
        this.locale = Locale.forLanguageTag(language);
        this.msg = msg;
        log.info("Locale: " + Locale.getDefault());
    }

    public String getMessage(String key, Object... args) {
        return msg.getMessage(
                key,
                args,
                locale);
    }
}
