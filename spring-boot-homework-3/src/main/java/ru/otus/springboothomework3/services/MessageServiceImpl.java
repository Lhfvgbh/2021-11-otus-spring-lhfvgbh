package ru.otus.springboothomework3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource msg;
    private final LocaleProvider localeProvider;

    @Autowired
    private MessageServiceImpl(MessageSource msg,
                               LocaleProvider localeProvider) {
        this.msg = msg;
        this.localeProvider = localeProvider;
    }

    public String getMessage(String key, Object... args) {
        return msg.getMessage(
                key,
                args,
                localeProvider.getLocale());
    }
}
