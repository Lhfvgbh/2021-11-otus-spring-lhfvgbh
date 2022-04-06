package ru.otus.homework_12.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework_12.models.*;
import ru.otus.homework_12.service.*;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование BookRestController")
@WebMvcTest(BookController.class)
public class BookControllerTest {

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

    @WithMockUser
    @DisplayName("load main page")
    @Test
    void indexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @DisplayName("load book by id")
    @Test
    void editPage() throws Exception {
        when(bookService.getBook(EXISTING_BOOK_ID)).thenReturn(EXISTING_BOOK);
        when(authorService.getAuthor(EXISTING_AUTHOR.getId())).thenReturn(EXISTING_AUTHOR);
        when(genreService.getGenreById(EXISTING_GENRE.getId())).thenReturn(EXISTING_GENRE);

        mockMvc.perform(get("/books/", EXISTING_BOOK_ID))
                .andExpect(status().isOk())
                .andReturn();
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ADMIN"}
    )
    @DisplayName("add new book")
    @Test
    void add() throws Exception {
        Book book = new Book(-1L, "New title", "New description", EXISTING_AUTHOR, EXISTING_GENRE);

        when(bookService.addNewBook(book)).thenReturn(book);

        mockMvc.perform(post("/books/add")
                .with(csrf())
                .param("title", "New title")
                .param("description", "New description")
                .param("author", String.valueOf(EXISTING_BOOK_AUTHOR_ID))
                .param("genre", String.valueOf(EXISTING_BOOK_GENRE_ID)))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ADMIN"}
    )
    @DisplayName("edit book")
    @Test
    void edit() throws Exception {
        Book book = new Book(EXISTING_BOOK_ID, "New title", "New description", EXISTING_AUTHOR, EXISTING_GENRE);

        when(bookService.editBook(book)).thenReturn(book);

        this.mockMvc.perform(post("/books/edit")
                .with(csrf())
                .param("id", String.valueOf(EXISTING_BOOK_ID))
                .param("title", "New title")
                .param("description", "New description"))
                .andExpect(status().isFound());
    }

}