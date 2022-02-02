package ru.otus.homework_4.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework_4.dao.BookDao;
import ru.otus.homework_4.domain.Book;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addNewBook(Book book) {
        log.info("Adding the book with the title: " + book.getTitle());
        bookDao.insert(book);
    }

    @Override
    public void deleteBook(long id) {
        log.info("Removing the book by identifier: " + id);
        bookDao.deleteById(id);
    }

    @Override
    public void editBook(Book book) {
        log.info("Updating book with the title: " + book.getTitle());
        bookDao.update(book);
    }

    @Override
    public Book getBook(long bookNumber) {
        log.info("Searching the book by identifier: " + bookNumber);
        return bookDao.getById(bookNumber);
    }

    @Override
    public Book getBookByTitle(String bookTitle) {
        log.info("Searching the book by title " + bookTitle);
        return bookDao.getByTitle(bookTitle);
    }

    @Override
    public int getTotalNumberOfBooks() {
        log.info("Counting all books in the library");
        return bookDao.count();
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("Getting all books from the library");
        return bookDao.getAll();
    }
}
