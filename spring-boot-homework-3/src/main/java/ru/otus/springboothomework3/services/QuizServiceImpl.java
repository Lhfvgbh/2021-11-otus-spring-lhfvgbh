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
    private final QuizIOService ioService;
    private final int throughput;

    @Autowired
    public QuizServiceImpl(QuestionService questionService,
                           ResultService resultService,
                           QuizIOService ioService,
                           @Value("${throughput}") int throughput) {
        this.questionService = questionService;
        this.resultService = resultService;
        this.ioService = ioService;
        this.throughput = throughput;
    }

    public void startQuiz() {
        try {
            Student student = getStudentProfile();
            QuizResult result = calculateAnswers(student);
            ioService.summarizeResult(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private Student getStudentProfile() {
        String firstname = ioService.getStudentFirstName();
        String lastname = ioService.getStudentLastName();

        return new Student(firstname, lastname);
    }

    private QuizResult calculateAnswers(Student student) throws Exception {
        Quiz quiz = questionService.buildQuiz();
        QuizResult result = new QuizResult();

        for (Question question : quiz.getQuestions()) {
            String answer = ioService.getAnswer(question.getQuestion());
            boolean isAnswerCorrect = resultService.checkAnswer(question, answer);
            result.acceptAnswer(isAnswerCorrect);
            ioService.checkAnswer(isAnswerCorrect);
        }

        result.calculateTotalResult(student, throughput);
        return result;
    }
}
