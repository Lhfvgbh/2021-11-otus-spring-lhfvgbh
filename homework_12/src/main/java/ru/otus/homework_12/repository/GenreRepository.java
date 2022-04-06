package ru.otus.homework_12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework_12.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
