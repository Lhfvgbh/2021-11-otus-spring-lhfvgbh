package ru.otus.homework_7.service;

import ru.otus.homework_7.models.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);
}
