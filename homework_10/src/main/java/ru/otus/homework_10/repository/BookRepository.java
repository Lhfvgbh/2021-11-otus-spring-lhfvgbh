package ru.otus.homework_10.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework_10.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre-graph")
    List<Book> findByTitle(String title);

    @EntityGraph(value = "book-author-genre-graph")
    @Override
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-author-genre-graph")
    @Override
    List<Book> findAll();
}
