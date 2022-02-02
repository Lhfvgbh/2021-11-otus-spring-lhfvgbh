package ru.otus.homework_4.dao;

import ru.otus.homework_4.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);
}
