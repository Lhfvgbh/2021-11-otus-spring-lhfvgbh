package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Student;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final IOMessageServiceImpl ioMessageService;

    @Autowired
    public StudentServiceImpl(IOMessageServiceImpl ioMessageService) {
        this.ioMessageService = ioMessageService;
    }

    @Override
    public Student readStudent() {
        ioMessageService.printLine("message.firstname");
        String firstname = ioMessageService.readLine();

        ioMessageService.printLine("message.lastname");
        String lastname = ioMessageService.readLine();

        return new Student(firstname, lastname);
    }
}
