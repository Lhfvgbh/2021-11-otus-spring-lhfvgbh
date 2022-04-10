package ru.otus.homework_9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework_9.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
