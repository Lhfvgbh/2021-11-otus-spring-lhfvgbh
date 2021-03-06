package ru.otus.springboothomework3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.*;

@Service
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final IOService ioService;
    private final int throughput;

    @Autowired
    public QuizAnswerServiceImpl(@Value("${throughput}") int throughput,
                                 IOService ioService) {
        this.ioService = ioService;
        this.throughput = throughput;
    }

    private boolean checkAnswer(Question question, String answer) {
        return question.getCorrectAnswer().equalsIgnoreCase(answer);
    }

    @Override
    public QuizResult calculateAnswers(Quiz quiz, Student student) {
        QuizResult result = new QuizResult(student, throughput);

        for (Question question : quiz.getQuestions()) {
            ioService.printLine(question.getQuestion());
            String answer = ioService.readLine();
            boolean isAnswerCorrect = checkAnswer(question, answer);
            result.acceptAnswer(isAnswerCorrect);
            ioService.printLine(String.valueOf(isAnswerCorrect));
        }
        return result;
    }
}
