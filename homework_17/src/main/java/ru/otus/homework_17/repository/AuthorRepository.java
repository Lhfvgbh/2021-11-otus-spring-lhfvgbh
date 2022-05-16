package ru.otus.homework_17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework_17.models.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByPenName(String penName);
}
