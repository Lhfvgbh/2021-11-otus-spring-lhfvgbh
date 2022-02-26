package ru.otus.homework_6.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_6.models.Genre;
import ru.otus.homework_6.service.GenreService;
import ru.otus.homework_6.service.OutputService;

import java.util.Collections;

@ShellComponent
public class GenreShellService {
    private final GenreService genreService;
    private final OutputService outputService;

    @Autowired
    public GenreShellService(GenreService genreService, OutputService outputService) {
        this.genreService = genreService;
        this.outputService = outputService;
    }

    @ShellMethod(key = "getAllGenres", value = "Print all available book genres")
    public void getAllGenres() {
        outputService.printList(Collections.singletonList(genreService.getAllGenres()));
    }

    @ShellMethod(key = "addGenre", value = "Add new genre for the book")
    public void addGenre(@ShellOption({"name", "n"}) String name) {
        genreService.addGenre(new Genre(-1L, name));
    }

}
