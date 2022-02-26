package ru.otus.homework_7.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_7.models.Book;
import ru.otus.homework_7.repository.BookRepository;

import java.util.List;

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
    public Book getBook(long id) {
        log.info("Searching the book by identifier: " + id);
        return bookRepository.findById(id).orElse(new Book());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBookByTitle(String bookTitle) {
        log.info("Searching the book by title: " + bookTitle);
        return bookRepository.findByTitle(bookTitle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        log.info("Getting all books from the library");
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        log.info("Removing the book by identifier: " + id);
        bookRepository.deleteById(id);
    }
}
