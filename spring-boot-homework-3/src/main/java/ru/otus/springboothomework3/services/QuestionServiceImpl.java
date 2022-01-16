package ru.otus.springboothomework3.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {

    private String filename;
    List<Question> questions;

    @Autowired
    public QuestionServiceImpl(MessageService messageService) {
        this.filename = messageService.getQuestionFile();
    }

    public Quiz readQuestions() {
        questions = new ArrayList<>();
        try {
            try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename)))) {
                String[] line;
                while ((line = reader.readNext()) != null) {
                    questions.add(new Question(line[0], line[1]));
                }
            }
        } catch (IOException |
                CsvValidationException ex) {
            log.error(String.format("File %s cannot be read!", filename));
        }
        return new Quiz(questions, questions.size());
    }
}
