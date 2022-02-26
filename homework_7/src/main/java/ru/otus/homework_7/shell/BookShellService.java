package ru.otus.homework_7.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_7.models.Book;
import ru.otus.homework_7.service.AuthorService;
import ru.otus.homework_7.service.BookService;
import ru.otus.homework_7.service.GenreService;
import ru.otus.homework_7.service.OutputService;

import java.util.Collections;

@ShellComponent
public class BookShellService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final OutputService outputService;

    @Autowired
    public BookShellService(BookService bookService, AuthorService authorService,
                            GenreService genreService, OutputService outputService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.outputService = outputService;
    }

    @ShellMethod(key = "getAllBooks", value = "Print all the books")
    public void getAllBooks() {
        outputService.printList(Collections.singletonList(bookService.getAllBooks()));
    }

    @ShellMethod(key = "addBook", value = "Add new book to the library")
    public void addBook(@ShellOption({"title", "t"}) String title,
                        @ShellOption({"description", "d"}) String description,
                        @ShellOption({"authorId", "a"}) long authorId,
                        @ShellOption({"genreId", "g"}) long genreId) {
        bookService.addNewBook(new Book(-1L, title, description, authorService.getAuthor(authorId),
                genreService.getGenreById(genreId)));
    }

    @ShellMethod(key = "getBookByName", value = "Get book information by book name")
    public void getBookByName(@ShellOption({"title", "t"}) String title) {
        outputService.printOne(bookService.getBookByTitle(title));
    }

    @ShellMethod(key = "getBookById", value = "Get book information by book id")
    public void getBookById(@ShellOption({"id", "i"}) long id) {
        outputService.printOne(bookService.getBook(id));
    }

    @ShellMethod(key = "deleteBook", value = "Delete book from the library")
    public void deleteBook(@ShellOption({"identifier", "id"}) long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(key = "updateBook", value = "Edit book in the library")
    public void updateBook(@ShellOption({"identifier", "i"}) long bookId,
                           @ShellOption({"title", "t"}) String title,
                           @ShellOption({"description", "d"}) String description,
                           @ShellOption({"authorId", "a"}) long authorId,
                           @ShellOption({"genreId", "g"}) long genreId) {
        bookService.editBook(new Book(bookId, title, description, authorService.getAuthor(authorId),
                genreService.getGenreById(genreId)));
    }
}
