package ru.otus.homework_4.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.domain.Book;
import ru.otus.homework_4.service.AuthorService;
import ru.otus.homework_4.service.BookService;

@ShellComponent
public class ShellService {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ShellService(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @ShellMethod(key = "getAllBooks", value = "Print all the books")
    public void getAllBooks() {
        for (Book book : bookService.getAllBooks()) {
            System.out.println(book);
        }
    }

    @ShellMethod(key = "getAllAuthors", value = "Print all the authors")
    public void getAllAuthors() {
        for (Author author : authorService.getAllAuthors()) {
            System.out.println(author);
        }
    }

    @ShellMethod(key = "addBook", value = "Add new book to the library")
    public void addBook(@ShellOption({"identifier", "id"}) long id,
                        @ShellOption({"title", "t"}) String title,
                        @ShellOption({"description", "d"}) String description,
                        @ShellOption({"authorId", "a"}) long authorId,
                        @ShellOption({"genreId", "g"}) long genreId) {
        bookService.addNewBook(new Book(id, title, description, authorId, genreId));
    }

    @ShellMethod(key = "getBookByName", value = "Get book information by book name")
    public void getBookByName(@ShellOption({"title", "t"}) String title) {
        System.out.println(bookService.getBookByTitle(title));
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
        bookService.editBook(new Book(bookId, title, description, authorId, genreId));
    }
}
