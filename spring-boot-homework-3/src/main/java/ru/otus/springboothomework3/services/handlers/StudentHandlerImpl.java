package ru.otus.springboothomework3.services.handlers;

import org.springframework.stereotype.Service;
import ru.otus.springboothomework3.models.Student;
import ru.otus.springboothomework3.services.IOMessageServiceImpl;

@Service
public class StudentHandlerImpl implements StudentHandler {
    private Student student;
    private final IOMessageServiceImpl ioMessageService;

    public StudentHandlerImpl(IOMessageServiceImpl ioMessageService) {
        this.ioMessageService = ioMessageService;
        this.student = null;
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        this.student = student;
        ioMessageService.printLine("message.hello", student.getFirstName());
    }
}
