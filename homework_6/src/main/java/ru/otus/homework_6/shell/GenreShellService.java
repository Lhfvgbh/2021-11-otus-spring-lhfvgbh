package ru.otus.homework_6.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_6.models.Genre;
import ru.otus.homework_6.service.GenreService;

@ShellComponent
public class GenreShellService {
    private final GenreService genreService;

    @Autowired
    public GenreShellService(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(key = "getAllGenres", value = "Print all available book genres")
    public void getAllGenres() {
        for (Genre genre : genreService.getAllGenres()) {
            System.out.println(genre);
        }
    }

    @ShellMethod(key = "addGenre", value = "Add new genre for the book")
    public void addGenre(@ShellOption({"name", "n"}) String name) {
        genreService.addGenre(new Genre(-1L, name));
    }

}
