package ru.otus.homework_18.service;

import ru.otus.homework_18.models.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);
}
