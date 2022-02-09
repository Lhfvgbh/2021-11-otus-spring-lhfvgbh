package ru.otus.homework_4.service;

import ru.otus.homework_4.domain.Book;

import java.util.List;

public interface BookService {

    void addNewBook(Book book);

    void deleteBook(long id);

    void editBook(Book book);

    Book getBook(long bookNumber);

    Book getBookByTitle(String bookTitle);

    int getTotalNumberOfBooks();

    List<Book> getAllBooks();
}
