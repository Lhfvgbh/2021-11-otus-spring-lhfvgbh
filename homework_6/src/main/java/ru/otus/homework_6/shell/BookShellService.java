package ru.otus.homework_6.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_6.models.Book;
import ru.otus.homework_6.service.AuthorService;
import ru.otus.homework_6.service.BookService;
import ru.otus.homework_6.service.GenreService;

@ShellComponent
public class BookShellService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookShellService(BookService bookService, AuthorService authorService,
                            GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(key = "getAllBooks", value = "Print all the books")
    public void getAllBooks() {
        for (Book book : bookService.getAllBooks()) {
            System.out.println(book);
        }
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
        System.out.println(bookService.getBookByTitle(title));
    }

    @ShellMethod(key = "getBookById", value = "Get book information by book id")
    public void getBookByName(@ShellOption({"id", "i"}) long id) {
        System.out.println(bookService.getBook(id));
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
