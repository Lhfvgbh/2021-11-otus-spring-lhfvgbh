package ru.otus.homework_10.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework_10.dto.BookDTO;
import ru.otus.homework_10.models.Book;
import ru.otus.homework_10.service.AuthorService;
import ru.otus.homework_10.service.BookService;
import ru.otus.homework_10.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/api/v1/books")
    public List<Book> getAllBook() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/v1/books/find/{title}")
    public List<Book> findBook(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/api/v1/books/{id}")
    public Book findBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Validated
    @PutMapping("/api/v1/books")
    public Book updateBook(@RequestBody BookDTO bookDTO) {
        return bookService.editBook(bookDTO.toDomainObject(
                authorService.getAuthor(bookDTO.getAuthorId()),
                genreService.getGenreById(bookDTO.getGenreId())));
    }

    @Validated
    @PostMapping("/api/v1/books")
    public Book saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.addNewBook(bookDTO.toDomainObject(
                authorService.getAuthor(bookDTO.getAuthorId()),
                genreService.getGenreById(bookDTO.getGenreId())));
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
