package ru.otus.springboothomework3.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.ResultTotal;
import ru.otus.springboothomework3.models.Student;

import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {

    final QuestionService questionService;
    final ResultService resultService;
    final MessageService messageService;
    @Getter
    private Student student;
    @Getter
    private ResultTotal result;

    @Autowired
    public QuizServiceImpl(QuestionService questionService, ResultService resultService, MessageService messageService) {
        this.questionService = questionService;
        this.resultService = resultService;
        this.messageService = messageService;
    }

    public ResultTotal startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageService.getWelcomeMessage());
        System.out.println(messageService.getFirstName());
        String firstname = scanner.nextLine();
        System.out.println(messageService.getLastName());
        String lastname = scanner.nextLine();
        System.out.println(messageService.getStartMessage());
        student = new Student(firstname, lastname);

        Quiz quiz = questionService.readQuestions();

        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestion());
            String answer = scanner.nextLine();
            System.out.println(resultService.checkAnswer(question, answer));
        }

        result = resultService.calculateTotalResult(student);

        System.out.println(messageService.getResult() + result.getStatus());
        System.out.println(messageService.getScore() + result.getScore() + "/" + quiz.getNumberOfQuestions());

        return result;
    }
}
