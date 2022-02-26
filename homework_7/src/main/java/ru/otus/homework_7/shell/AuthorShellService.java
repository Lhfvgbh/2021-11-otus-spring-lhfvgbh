package ru.otus.homework_7.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_7.models.Author;
import ru.otus.homework_7.service.AuthorService;
import ru.otus.homework_7.service.OutputService;

import java.util.Collections;

@ShellComponent
public class AuthorShellService {
    private final AuthorService authorService;
    private final OutputService outputService;

    @Autowired
    public AuthorShellService(AuthorService authorService, OutputService outputService) {
        this.authorService = authorService;
        this.outputService = outputService;
    }

    @ShellMethod(key = "getAllAuthors", value = "Print all the authors")
    public void getAllAuthors() {
        outputService.printList(Collections.singletonList(authorService.getAllAuthors()));
    }

    @ShellMethod(key = "addAuthor", value = "Add new author")
    public void addAuthor(@ShellOption({"name", "n"}) String name) {
        authorService.addNewAuthor(new Author(-1L, name));
    }
}
