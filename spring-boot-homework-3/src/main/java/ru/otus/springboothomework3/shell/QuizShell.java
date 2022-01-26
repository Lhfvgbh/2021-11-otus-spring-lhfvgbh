package ru.otus.springboothomework3.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.QuizResult;
import ru.otus.springboothomework3.models.Student;
import ru.otus.springboothomework3.services.*;

@ShellComponent
@Slf4j
public class QuizShell {

    Student student;
    private final QuestionService questionService;
    private final QuizAnswerService quizAnswerService;
    private final IOMessageServiceImpl ioMessageService;
    private final MessageServiceImpl messageService;

    @Autowired
    public QuizShell(
            QuestionService questionService,
            QuizAnswerService quizAnswerService,
            IOMessageServiceImpl ioMessageService, MessageServiceImpl messageService) {
        this.questionService = questionService;
        this.quizAnswerService = quizAnswerService;
        this.ioMessageService = ioMessageService;
        this.messageService = messageService;
    }

    @ShellMethod(key = "start", value = "Start the Quiz")
    @ShellMethodAvailability(value = "islogin")
    public void start() {
        try {
            ioMessageService.printLine("message.start");
            Quiz quiz = questionService.buildQuiz();
            QuizResult result = quizAnswerService.calculateAnswers(quiz, student);
            ioMessageService.printLine("message.result", result.getStatus());
            ioMessageService.printLine("message.score", result.getScore(), "/", result.getQuestionCounter());
        } catch (Exception e) {
            ioMessageService.printLine(e.getMessage());
            log.error(e.getMessage());
        }
    }

    private Availability islogin() {
        return student == null ?
                Availability.unavailable(messageService.getMessage("message.login")) :
                Availability.available();
    }

    @ShellMethod(key = "login", value = "Login to the Quiz")
    public void login(@ShellOption({"firstname", "f"}) String firstname,
                      @ShellOption({"lastname", "l"}) String lastname) {
        this.student = new Student(firstname, lastname);
        ioMessageService.printLine("message.hello", firstname);
    }

}
