package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class LocaleProviderImpl implements LocaleProvider {
    @Getter
    private final Locale locale;

    public LocaleProviderImpl(@Value("${default.language}") String language) {
        this.locale = Locale.forLanguageTag(language);
        log.info("Locale: " + this.locale);
    }
}
