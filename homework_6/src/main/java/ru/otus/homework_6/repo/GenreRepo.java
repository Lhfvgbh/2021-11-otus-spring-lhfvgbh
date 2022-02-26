package ru.otus.homework_6.repo;

import ru.otus.homework_6.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepo {

    long count();

    Genre save(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getAll();

    void delete(Genre genre);
}
