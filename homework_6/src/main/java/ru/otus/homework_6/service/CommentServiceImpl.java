package ru.otus.homework_6.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_6.models.Author;
import ru.otus.homework_6.models.Book;
import ru.otus.homework_6.models.Comment;
import ru.otus.homework_6.repo.CommentRepo;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalNumberOfComments() {
        return commentRepo.count();
    }

    @Override
    @Transactional
    public Comment addNewComment(Comment comment) {
        log.info("Adding new comment for the book: " + comment.getBook().getTitle());
        return commentRepo.save(comment);
    }

    @Override
    @Transactional
    public Comment editComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(long id) {
        return commentRepo.getById(id).orElse(new Comment());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsForBook(long bookId) {
        return commentRepo.getAllByBook(bookId);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        Comment comment = commentRepo.getById(id).orElse(null);
        if (comment != null) {
            commentRepo.delete(comment);
            log.info("Deleting author with id: " + id);
        }
    }
}
