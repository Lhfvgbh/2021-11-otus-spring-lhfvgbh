package ru.otus.homework_10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework_10.controller.rest.BookRestController;
import ru.otus.homework_10.dto.BookDTO;
import ru.otus.homework_10.models.Author;
import ru.otus.homework_10.models.Book;
import ru.otus.homework_10.models.Genre;
import ru.otus.homework_10.service.AuthorService;
import ru.otus.homework_10.service.BookService;
import ru.otus.homework_10.service.GenreService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование BookRestController")
@WebMvcTest(BookRestController.class)
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

    @DisplayName("load main page")
    @Test
    void indexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @DisplayName("load book by id")
    @Test
    void editPage() throws Exception {
        when(bookService.getBookById(EXISTING_BOOK_ID)).thenReturn(EXISTING_BOOK);
        when(authorService.getAuthor(EXISTING_AUTHOR.getId())).thenReturn(EXISTING_AUTHOR);
        when(genreService.getGenreById(EXISTING_GENRE.getId())).thenReturn(EXISTING_GENRE);

        mockMvc.perform(get("/api/v1/books/", EXISTING_BOOK_ID))
                .andExpect(status().isOk())
                .andReturn();
    }

    @DisplayName("add new book")
    @Test
    void add() throws Exception {
        Book book = new Book(-1L, "New title", "New description", EXISTING_AUTHOR, EXISTING_GENRE);
        BookDTO bookDto = BookDTO.fromDomainObject(book);

        when(bookService.addNewBook(book)).thenReturn(book);
        when(authorService.getAuthor(EXISTING_AUTHOR.getId())).thenReturn(EXISTING_AUTHOR);
        when(genreService.getGenreById(EXISTING_GENRE.getId())).thenReturn(EXISTING_GENRE);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(post("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        verify(bookService, times(1)).addNewBook(book);
    }

    @DisplayName("edit book")
    @Test
    void edit() throws Exception {
        Book book = new Book(EXISTING_BOOK_ID, "New title", EXISTING_BOOK_DESCRIPTION, EXISTING_AUTHOR, EXISTING_GENRE);
        BookDTO bookDto = BookDTO.fromDomainObject(book);

        when(bookService.editBook(book)).thenReturn(book);
        when(authorService.getAuthor(EXISTING_AUTHOR.getId())).thenReturn(EXISTING_AUTHOR);
        when(genreService.getGenreById(EXISTING_GENRE.getId())).thenReturn(EXISTING_GENRE);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(put("/api/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        verify(bookService, times(1)).editBook(book);
    }

}
