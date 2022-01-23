package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class LocaleProviderImpl implements LocaleProvider {
    @Getter
    private Locale locale;

    public LocaleProviderImpl(@Value("${default.language}") String language) {
        this.locale = Locale.forLanguageTag(language);
        log.info("Locale: " + this.locale);
    }
}
