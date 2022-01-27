package ru.otus.springboothomework3.services.providers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.springboothomework3.exceptions.QuizException;

@Slf4j@Component
public class FilenameProviderImpl implements FilenameProvider {
    @Getter
    private final String filename;

    @Autowired
    public FilenameProviderImpl(@Value("${filename.template}") String filenameTemplate,
                                LocaleProvider localeProvider) throws QuizException {
        String language = localeProvider.getLocale().getLanguage();
        if (language.isEmpty()) {
            language = "en";
        }
        if (language.length() > 2) {
            throw new QuizException("Incorrect language!");
        }
        this.filename = filenameTemplate.replace("*", language);
        log.info("File: " + filename);
    }
}
