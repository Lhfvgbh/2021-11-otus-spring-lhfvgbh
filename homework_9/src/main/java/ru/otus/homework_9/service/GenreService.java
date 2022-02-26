package ru.otus.homework_9.service;

import ru.otus.homework_9.models.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);
}
