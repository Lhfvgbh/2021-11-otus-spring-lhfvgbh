package ru.otus.homework_9.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework_9.models.Author;
import ru.otus.homework_9.models.Book;
import ru.otus.homework_9.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тестирование BookRepoJdbc")
@DataJpaTest
class BookRepositoryTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Гарри Поттер и философский камень";
    private static final int EXISTING_BOOK_TITLE_COUNT = 1;
    private static final long EXISTING_BOOK_AUTHOR_ID = 1L;
    private static final long EXISTING_BOOK_GENRE_ID = 1L;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("method count")
    @Test
    void shouldReturnBookCount() {
        long actualResult = bookRepository.count();
        assertThat(actualResult).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("method save")
    @Test
    void shouldInsertBook() {
        Author author = testEntityManager.find(Author.class, EXISTING_BOOK_AUTHOR_ID);
        Genre genre = testEntityManager.find(Genre.class, EXISTING_BOOK_GENRE_ID);
        Book expectedBook = new Book(null, "Гарри Поттер и узник Азкабана",
                "Описание 3й книги", author, genre);
        Book actualBook = bookRepository.save(expectedBook);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("method save (update)")
    @Test
    void shouldUpdateBook() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        expectedBook.setTitle("New title");
        Book actualBook = bookRepository.save(expectedBook);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnBookById() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Book actualBook = bookRepository.getById(EXISTING_BOOK_ID);
        assertEquals(expectedBook.getId(), actualBook.getId());
        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
        assertEquals(expectedBook.getDescription(), actualBook.getDescription());
        assertEquals(expectedBook.getAuthor().getId(), actualBook.getAuthor().getId());
        assertEquals(expectedBook.getGenre().getId(), actualBook.getGenre().getId());
    }

    @DisplayName("method getByTitle")
    @Test
    void shouldReturnBookByTitle() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        List<Book> actualBookList = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertThat(actualBookList).contains(expectedBook);
        assertThat(actualBookList).isNotNull().hasSize(EXISTING_BOOK_TITLE_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthor().getId() > 0)
                .allMatch(s -> s.getGenre().getId() > 0);
    }

    @DisplayName("method getAllBooks")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);

        List<Book> actualBookList = bookRepository.findAll();
        assertThat(actualBookList).contains(expectedBook);
        assertThat(actualBookList).isNotNull().hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthor().getId() > 0)
                .allMatch(s -> s.getGenre().getId() > 0);
    }

    @DisplayName("method deleteById")
    @Test
    void shouldDeleteBookById() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        testEntityManager.detach(expectedBook);
        bookRepository.deleteById(EXISTING_BOOK_ID);
        assertNull(testEntityManager.find(Book.class, EXISTING_BOOK_ID));
    }

}