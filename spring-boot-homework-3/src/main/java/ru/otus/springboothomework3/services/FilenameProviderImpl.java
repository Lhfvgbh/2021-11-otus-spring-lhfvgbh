package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FilenameProviderImpl implements FileNameProvider {
    @Getter
    private final String filename;

    public FilenameProviderImpl(@Value("${filename.template}") String filenameTemplate, MessageService messageService) {
        this.filename = filenameTemplate.replace("*", messageService.getMessage("file.questions.locale"));
    }
}
