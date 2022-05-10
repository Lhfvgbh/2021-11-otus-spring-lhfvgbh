package ru.otus.homework_17.service;

import ru.otus.homework_17.models.Comment;

import java.util.List;

public interface CommentService {

    long getTotalNumberOfComments();

    Comment addNewComment(Comment comment);

    Comment editComment(Comment comment);

    Comment getCommentById(long id);

    List<Comment> getAllCommentsForBook(long bookId);

    void deleteComment(long id);

}
