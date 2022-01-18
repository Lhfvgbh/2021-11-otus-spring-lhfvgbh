package ru.otus.springboothomework3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Question;
import ru.otus.springboothomework3.models.Quiz;
import ru.otus.springboothomework3.models.ResultTotal;
import ru.otus.springboothomework3.models.Student;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final ResultService resultService;
    private final IOService ioService;

    @Autowired
    public QuizServiceImpl(QuestionService questionService,
                           ResultService resultService,
                           IOService ioService) {
        this.questionService = questionService;
        this.resultService = resultService;
        this.ioService = ioService;
    }

    public void startQuiz() throws Exception {
        Student student = getStudentProfile();

        getQuiz();

        ResultTotal result = resultService.calculateTotalResult(student);

        ioService.summarizeResult(result);
    }

    public Student getStudentProfile() {
        String firstname = ioService.getStudentFirstName();
        String lastname = ioService.getStudentLastName();

        return new Student(firstname, lastname);
    }

    public void getQuiz() throws Exception {
        Quiz quiz = questionService.getQuizQuestions();

        for (Question question : quiz.getQuestions()) {
            String answer = ioService.getAnswer(question.getQuestion());
            ioService.checkAnswer(resultService.checkAnswer(question, answer));
        }
    }
}
