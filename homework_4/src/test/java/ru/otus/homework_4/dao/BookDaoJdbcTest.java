package ru.otus.homework_4.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.domain.Book;
import ru.otus.homework_4.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Гарри Поттер и философский камень";
    private static final String EXISTING_BOOK_DESCRIPTION = "первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.";
    private static final Author EXISTING_BOOK_AUTHOR = new Author(2, "Джоан Роулинг");
    private static final Genre EXISTING_BOOK_GENRE = new Genre(1, "Сказка");

    @Autowired
    private BookDaoJdbc bookDao;

    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @DisplayName("method count")
    @Test
    void shouldReturnBookCount() {
        int actualResult = bookDao.count();
        assertThat(actualResult).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("method insert")
    @Test
    void shouldInsertBook() {
        Book expectedResult = new Book(4, "Гарри Поттер и узник Азкабана", "Описание 3й книги", EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        bookDao.insert(expectedResult);
        Book actualResult = bookDao.getById(expectedResult.getId());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("method getByName")
    @Test
    void shouldReturnBookByTitle() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        Book actualBook = bookDao.getByTitle(expectedBook.getTitle());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("method getAllBooks")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualBookList).contains(expectedBook);
    }

    @DisplayName("method update")
    @Test
    void shouldUpdateBook() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, "new book description", EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        bookDao.update(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("method deleteById")
    @Test
    void shouldDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))

                .doesNotThrowAnyException();
        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}