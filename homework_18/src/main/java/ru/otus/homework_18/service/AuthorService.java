package ru.otus.homework_18.service;

import ru.otus.homework_18.models.Author;

import java.util.List;

public interface AuthorService {

    Author addNewAuthor(Author author);

    Author addNewAuthor(String name);

    Author editAuthor(Author author);

    Author getAuthor(long authorNumber);

    Author getAuthorByName(String authorName);

    List<Author> getAllAuthors();

    void deleteAuthor(long id);
}
