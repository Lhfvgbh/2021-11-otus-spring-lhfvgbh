package ru.otus.homework_4.dao;

import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.domain.Book;

import java.util.List;

public interface AuthorDao {

    int count();

    void insert(Author author);

    Author getById(long id);

    Author getByName(String name);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);
}
