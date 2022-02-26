package ru.otus.homework_7.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(value = "comment-book-graph")
    List<Comment> findByBookId(Long id);

    @EntityGraph(value = "comment-book-graph")
    @Override
    Optional<Comment> findById(Long id);

    @EntityGraph(value = "comment-book-graph")
    @Override
    List<Comment> findAll();
}
