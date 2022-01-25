package ru.otus.springboothomework3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springboothomework3.services.QuizService;

@SpringBootApplication
public class SpringBootHomework3Application {

    public static void main(String[] args) throws Exception{

        ApplicationContext ctx = SpringApplication.run(SpringBootHomework3Application.class, args);

        QuizService service = ctx.getBean(QuizService.class);
        service.startQuiz();
    }

}
