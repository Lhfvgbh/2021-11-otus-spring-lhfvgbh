package ru.otus.homework_18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_18.exception.FallbackException;
import ru.otus.homework_18.models.Author;
import ru.otus.homework_18.models.Book;
import ru.otus.homework_18.models.Genre;
import ru.otus.homework_18.repository.BookRepository;

import java.util.*;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book addNewBook(Book book) {
        log.info("Adding the book with the title: " + book.getTitle());
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book editBook(Book book) {
        log.info("Updating book with the title: " + book.getTitle());
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand
    public Book getBook(long id) {
        timer();
        log.info("Searching the book by identifier: " + id);
        return bookRepository.findById(id).orElse(new Book());
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand
    public List<Book> getBookByTitle(String bookTitle) {
        timer();
        log.info("Searching the book by title: " + bookTitle);
        return bookRepository.findByTitle(bookTitle);
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(fallbackMethod = "getBooksFallback")
    public List<Book> getAllBooks() {
        timer();
        log.info("Getting all books from the library");
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        log.info("Removing the book by identifier: " + id);
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksFallback() {
        Book book = new Book(1L, "Please update this page",
                "Please update this page",
                new Author(1L, "Please update this page"),
                new Genre(1L, "Please update this page"));
        List<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }

    public void timer() {
        try {
            if (new Random().nextBoolean()) {
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
