package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.services.handlers.StudentHandler;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuestionService questionService;
    private final QuizAnswerService quizAnswerService;
    private final StudentHandler studentHandler;
    private final IOMessageServiceImpl ioMessageService;

    @Autowired
    public QuizServiceImpl(QuestionService questionService,
                           QuizAnswerService quizAnswerService,
                           StudentHandler studentHandler,
                           IOMessageServiceImpl ioMessageService) {
        this.questionService = questionService;
        this.quizAnswerService = quizAnswerService;
        this.studentHandler = studentHandler;
        this.ioMessageService = ioMessageService;
    }

    public void startQuiz() {
        try {
            ioMessageService.printLine("message.start");
            Quiz quiz = questionService.buildQuiz();
            QuizResult result = quizAnswerService.calculateAnswers(quiz, studentHandler.getStudent());
            ioMessageService.printLine("message.result", result.getStatus());
            ioMessageService.printLine("message.score", result.getScore(), "/", result.getQuestionCounter());
        } catch (Exception e) {
            ioMessageService.printLine(e.getMessage());
            log.error(e.getMessage());
        }
    }
}
