package ru.otus.homework_4.dao;

import ru.otus.homework_4.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    void insert(Book book);

    Book getById(long id);

    Book getByTitle(String title);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);
}
