package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
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
        if (args.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (Object o : args) {
                builder.append(o);
            }
            return msg.getMessage(
                    key,
                    args,
                    localeProvider.getLocale()) + builder.toString();
        } else {
            return msg.getMessage(
                    key,
                    args,
                    localeProvider.getLocale());
        }
    }
}
