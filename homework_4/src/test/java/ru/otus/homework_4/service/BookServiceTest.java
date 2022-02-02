package ru.otus.homework_4.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework_4.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование BookService")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Slf4j
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Гарри Поттер и философский камень";
    private static final String EXISTING_BOOK_DESCRIPTION = "первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.";
    private static final int EXISTING_BOOK_AUTHOR_ID = 2;
    private static final int EXISTING_BOOK_GENRE_ID = 1;

    @Test
    @DisplayName("Get book from the library by ID")
    void shouldReturnBookById() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        Book actualResult = bookService.getBook(expectedResult.getId());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get book from the library by title")
    void shouldReturnBookByTitle() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        Book actualResult = bookService.getBookByTitle(expectedResult.getTitle());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get all available books from the library")
    void shouldReturnExpectedBooksList() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_GENRE_ID);
        List<Book> actualResult = bookService.getAllBooks();
        assertThat(actualResult.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualResult).contains(expectedResult);
    }
}
