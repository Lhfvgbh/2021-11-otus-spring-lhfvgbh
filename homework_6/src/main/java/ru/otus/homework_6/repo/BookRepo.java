package ru.otus.homework_6.repo;

import ru.otus.homework_6.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {

    long count();

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    void deleteById(long id);
}
