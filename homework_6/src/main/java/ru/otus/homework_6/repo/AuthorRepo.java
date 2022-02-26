package ru.otus.homework_6.repo;

import ru.otus.homework_6.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepo {

    long count();

    Author save(Author author);

    Optional<Author> getById(long id);

    Author getByName(String name);

    List<Author> getAll();

    void delete(Author author);
}
