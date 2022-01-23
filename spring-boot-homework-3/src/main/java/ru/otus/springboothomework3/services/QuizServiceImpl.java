package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final QuizAnswerService quizAnswerService;
    private final StudentService studentService;
    private final IOMessageService ioMessageService;

    @Autowired
    public QuizServiceImpl(QuestionService questionService,
                           QuizAnswerService quizAnswerService,
                           StudentService studentService,
                           IOMessageService ioMessageService) {
        this.questionService = questionService;
        this.quizAnswerService = quizAnswerService;
        this.studentService = studentService;
        this.ioMessageService = ioMessageService;
    }

    public void startQuiz() {
        try {
            Quiz quiz = questionService.buildQuiz();
            ioMessageService.printLine("message.welcome");
            Student student = studentService.readStudent();
            ioMessageService.printLine("message.start");
            QuizResult result = quizAnswerService.calculateAnswers(quiz, student);
            ioMessageService.printLine("message.result", result.getStatus());
            ioMessageService.printLine("message.score", result.getScore(), "/", result.getQuestionCounter());
        } catch (Exception e) {
            ioMessageService.printLine(e.getMessage());
            log.error(e.getMessage());
        }
    }
}
