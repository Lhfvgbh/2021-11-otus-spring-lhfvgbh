package ru.otus.homework_16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework_16.dto.BookDTO;
import ru.otus.homework_16.models.Author;
import ru.otus.homework_16.models.Book;
import ru.otus.homework_16.models.Genre;
import ru.otus.homework_16.service.AuthorService;
import ru.otus.homework_16.service.BookService;
import ru.otus.homework_16.service.GenreService;

import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("")
    public String listBooksPage(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/find")
    public String findBookPage() {
        return "findBook";
    }

    @GetMapping("/findBook")
    public String findBook(@RequestParam(value="title", required=false) String title, Model model) {
        List<Book> books = bookService.getBookByTitle(title);
        model.addAttribute("books", books);
        return "findBook";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBook(id);
        List<Author> authors = authorService.getAllAuthors();
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @Validated
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.editBook(book);
        return "redirect:";
    }

    @GetMapping("/add")
    public String addBookPage(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "addBook";
    }

    @Validated
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDTO book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        bookService.addNewBook(book.toDomainObject());
        return "redirect:";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return "redirect:";
    }
}
