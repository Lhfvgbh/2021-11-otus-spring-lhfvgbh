package ru.otus.homework_6.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_6.models.Book;
import ru.otus.homework_6.models.Comment;

import java.util.List;

public interface CommentService {

    long getTotalNumberOfComments();

    Comment addNewComment(Comment comment);

    Comment editComment(Comment comment);

    Comment getCommentById(long id);

    List<Comment> getAllCommentsForBook(long bookId);

    void deleteComment(long id);

}
