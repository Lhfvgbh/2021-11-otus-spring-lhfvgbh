package ru.otus.homework_6.repo;

import ru.otus.homework_6.models.Book;
import ru.otus.homework_6.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepo {

    long count();

    Comment save(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getAllByBook(long bookId);

    void delete(Comment comment);
}
