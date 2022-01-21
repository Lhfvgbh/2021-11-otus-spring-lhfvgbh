package ru.otus.springboothomework3.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Student;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final MessageService messageService;
    private final IOService ioService;

    @Autowired
    public StudentServiceImpl(MessageService messageService, IOService ioService) {
        this.messageService = messageService;
        this.ioService = ioService;
    }

    @Override
    public Student readStudent() {
        ioService.printLine(messageService.getMessage("message.firstname"));
        String firstname = ioService.readLine();

        ioService.printLine(messageService.getMessage("message.lastname"));
        String lastname = ioService.readLine();

        return new Student(firstname, lastname);
    }
}
