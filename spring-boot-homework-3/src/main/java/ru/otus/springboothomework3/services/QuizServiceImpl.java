package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final ResultService resultService;
    private final StudentService studentService;
    private final MessageService messageService;
    private final IOService ioService;

    @Autowired
    public QuizServiceImpl(QuestionService questionService,
                           ResultService resultService,
                           StudentService studentService,
                           IOService ioService,
                           MessageService messageService) {
        this.questionService = questionService;
        this.resultService = resultService;
        this.studentService = studentService;
        this.messageService = messageService;
        this.ioService = ioService;
    }

    public void startQuiz() {
        try {
            Quiz quiz = questionService.buildQuiz();
            ioService.printLine(messageService.getMessage("message.welcome"));
            Student student = studentService.readStudent();
            ioService.printLine(messageService.getMessage("message.start"));
            QuizResult result = resultService.calculateAnswers(quiz, student);
            ioService.printLine(messageService.getMessage("message.result") + result.getStatus());
            ioService.printLine(messageService.getMessage("message.score") + result.getScore() + "/" + result.getQuestionCounter());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
