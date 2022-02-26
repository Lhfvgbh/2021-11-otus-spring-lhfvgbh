package ru.otus.homework_6.service;

import ru.otus.homework_6.models.Book;

import java.util.List;

public interface BookService {

    Book addNewBook(Book book);

    Book editBook(Book book);

    Book getBook(long bookNumber);

    List<Book> getBookByTitle(String bookTitle);

    List<Book> getAllBooks();

    void deleteBook(long id);
}
