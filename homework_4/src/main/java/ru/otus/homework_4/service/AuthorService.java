package ru.otus.homework_4.service;

import ru.otus.homework_4.domain.Author;

import java.util.List;

public interface AuthorService {

    void addNewAuthor(Author author);

    void deleteAuthor(long id);

    void editAuthor(Author author);

    Author getAuthor(long authorNumber);

    Author getAuthorByName(String authorName);

    int getTotalNumberOfAuthors();

    List<Author> getAllAuthors();
}
