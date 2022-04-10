package ru.otus.homework_12.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework_12.controller.BookController;
import ru.otus.homework_12.models.*;
import ru.otus.homework_12.service.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование Authentication")
@WebMvcTest(BookController.class)
public class BookSecurityTest {

    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Гарри Поттер и философский камень";
    private static final String EXISTING_BOOK_DESCRIPTION = "первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.";
    private static final long EXISTING_BOOK_AUTHOR_ID = 2L;
    private static final long EXISTING_BOOK_GENRE_ID = 1L;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Джоан Роулинг";
    private static final String EXISTING_BOOK_GENRE_NAME = "Фентези";

    private static final Author EXISTING_AUTHOR = new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
    private static final Genre EXISTING_GENRE = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
    private static final Book EXISTING_BOOK = new Book(EXISTING_BOOK_ID,
            EXISTING_BOOK_TITLE,
            EXISTING_BOOK_DESCRIPTION,
            EXISTING_AUTHOR,
            EXISTING_GENRE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private void setupMock() {
        when(bookService.getBook(EXISTING_BOOK_ID)).thenReturn(EXISTING_BOOK);
        when(authorService.getAuthor(EXISTING_AUTHOR.getId())).thenReturn(EXISTING_AUTHOR);
        when(genreService.getGenreById(EXISTING_GENRE.getId())).thenReturn(EXISTING_GENRE);
    }

    private Book setupMockWithBook() {
        Book book = new Book(EXISTING_BOOK_ID, "New title", "New description", EXISTING_AUTHOR, EXISTING_GENRE);
        when(bookService.addNewBook(book)).thenReturn(book);
        when(bookService.editBook(book)).thenReturn(book);
        return book;
    }

    @DisplayName("Check authentication no access")
    @ParameterizedTest
    @CsvSource({"/,''",
            "/books/add/,''",
            "/books/find/,''",
            "/books/,''",
            "/books/findBook/," + EXISTING_BOOK_TITLE,
            "/books/findBook/,''",
            "/books/edit/,''",
            "/books/remove/,''"})
    void checkNoAccess(String urlTemplate, String param) throws Exception {
        mockMvc.perform(get(urlTemplate, param))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @WithMockUser
    @DisplayName("Check authentication for no param requests")
    @ParameterizedTest
    @CsvSource({"/",
            "/books/add/",
            "/books/find/",
            "/books/",
            "/books/findBook/"})
    void checkNoParamRequests(String urlTemplate) throws Exception {
        setupMock();
        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser
    @DisplayName("Check authentication for value param requests")
    @ParameterizedTest
    @CsvSource({"/books/edit/,id," + EXISTING_BOOK_ID,
            "/books/findBook/,title," + EXISTING_BOOK_TITLE})
    void checkValueParamRequests(String urlTemplate, String paramName, String param) throws Exception {
        setupMock();
        mockMvc.perform(get(urlTemplate)
                .with(csrf())
                .param(paramName, param))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ADMIN"}
    )
    @DisplayName("Check authentication for book requests")
    @ParameterizedTest
    @CsvSource({"/books/add/",
            "/books/edit/",
            "/books/remove/"})
    void checkBookRequests(String urlTemplate) throws Exception {
        Book book = setupMockWithBook();

        mockMvc.perform(post(urlTemplate)
                .with(csrf())
                .param("id", book.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

}