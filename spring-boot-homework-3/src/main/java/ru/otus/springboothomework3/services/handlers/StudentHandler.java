package ru.otus.springboothomework3.services.handlers;

import ru.otus.springboothomework3.models.Student;

public interface StudentHandler {
    Student getStudent();

    void updateStudent(Student student);
}
