package ru.otus.homework_17.service;

import ru.otus.homework_17.models.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);
}
