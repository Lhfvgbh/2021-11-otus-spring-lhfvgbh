package ru.otus.homework_4.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework_4.dao.BookDao;
import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.domain.Book;
import ru.otus.homework_4.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тестирование BookService")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Slf4j
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Гарри Поттер и философский камень";
    private static final String EXISTING_BOOK_DESCRIPTION = "первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.";
    private static final Author EXISTING_BOOK_AUTHOR = new Author(2, "Джоан Роулинг");
    private static final Genre EXISTING_BOOK_GENRE = new Genre(1, "Сказка");

    @BeforeEach
    private void setupMock() {
        Book book = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        when(bookDao.getById(EXISTING_BOOK_ID)).thenReturn(book);
        when(bookDao.getByTitle(EXISTING_BOOK_TITLE)).thenReturn(book);
        when(bookDao.getAll()).thenReturn(Arrays.asList(book, book));
    }

    @Test
    @DisplayName("Get book from the library by ID")
    void shouldReturnBookById() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        Book actualResult = bookService.getBook(expectedResult.getId());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get book from the library by title")
    void shouldReturnBookByTitle() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        Book actualResult = bookService.getBookByTitle(expectedResult.getTitle());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get all available books from the library")
    void shouldReturnExpectedBooksList() {
        Book expectedResult = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_DESCRIPTION, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        List<Book> actualResult = bookService.getAllBooks();
        assertThat(actualResult.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualResult).contains(expectedResult);
    }
}
