package ru.otus.homework_6.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_6.models.Author;
import ru.otus.homework_6.service.AuthorService;

@ShellComponent
public class AuthorShellService {
    private final AuthorService authorService;

    @Autowired
    public AuthorShellService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(key = "getAllAuthors", value = "Print all the authors")
    public void getAllAuthors() {
        for (Author author : authorService.getAllAuthors()) {
            System.out.println(author);
        }
    }

    @ShellMethod(key = "addAuthor", value = "Add new author")
    public void addAuthor(@ShellOption({"name", "n"}) String name) {
        Author a = new Author();
        a.setPenName(name);
        a.setId(-1L);
        authorService.addNewAuthor(a);
        //authorService.addNewAuthor(new Author(-1, name));
    }
}
