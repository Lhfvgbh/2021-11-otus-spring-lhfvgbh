package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.spring.domain.Clothes;
import ru.otus.spring.integration.Atelier;
import ru.otus.spring.service.HelpService;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class Homework15Application {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Homework15Application.class);

        Atelier atelier = ctx.getBean(Atelier.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            try {
                Thread.sleep(5000);
                pool.execute(() -> {
                    Collection<Clothes> clothes = atelier.process(HelpService.generateCloth());
                    System.out.println("Ready item: " + clothes.stream()
                            .map(Clothes::getItemName)
                            .collect(Collectors.joining(",")));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
