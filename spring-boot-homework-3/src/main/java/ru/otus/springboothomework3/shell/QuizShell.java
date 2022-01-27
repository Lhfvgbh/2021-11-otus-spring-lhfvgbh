package ru.otus.springboothomework3.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.springboothomework3.models.Student;
import ru.otus.springboothomework3.services.*;
import ru.otus.springboothomework3.services.handlers.StudentHandler;

@ShellComponent
public class QuizShell {

    private final MessageService messageService;
    private final StudentHandler studentHandler;
    private final QuizService quizService;

    @Autowired
    public QuizShell(MessageService messageService,
                     StudentHandler studentHandler,
                     QuizService quizService) {
        this.messageService = messageService;
        this.studentHandler = studentHandler;
        this.quizService = quizService;
    }

    @ShellMethod(key = "start", value = "Start the Quiz")
    @ShellMethodAvailability(value = "isLogin")
    public void start() {
        quizService.startQuiz();
    }

    private Availability isLogin() {
        return studentHandler.getStudent() == null ?
                Availability.unavailable(messageService.getMessage("message.login")) :
                Availability.available();
    }

    @ShellMethod(key = "login", value = "Login to the Quiz")
    public void login(@ShellOption({"firstname", "f"}) String firstname,
                      @ShellOption({"lastname", "l"}) String lastname) {
        studentHandler.updateStudent(new Student(firstname, lastname));
    }

}
