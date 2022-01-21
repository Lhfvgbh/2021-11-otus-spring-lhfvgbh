package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.exceptions.QuizException;

@Slf4j
@Service
public class FilenameProviderImpl implements FileNameProvider {
    @Getter
    private final String filename;

    @Autowired
    public FilenameProviderImpl(@Value("${filename.template}") String filenameTemplate,
                                @Value("${default.language}") String language) throws QuizException {
        if (language.isEmpty()) {
            language = "en";
        }
        if (language.length() > 2) {
            throw new QuizException("Incorrect language! Please use 'ru' or 'en'.");
        }
        this.filename = filenameTemplate.replace("*", language);
        log.info("File: " + filename);
    }
}
