package ru.otus.homework_7.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_7.models.Comment;
import ru.otus.homework_7.repository.CommentRepository;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalNumberOfComments() {
        return commentRepository.count();
    }

    @Override
    @Transactional
    public Comment addNewComment(Comment comment) {
        log.info("Adding new comment for the book: " + comment.getBook().getTitle());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment editComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsForBook(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        Comment comment = commentRepository.getById(id);
        if (comment.getId() > 0) {
            commentRepository.delete(comment);
            log.info("Deleting author with id: " + id);
        }
    }
}
