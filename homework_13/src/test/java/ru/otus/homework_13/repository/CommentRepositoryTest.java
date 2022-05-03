package ru.otus.homework_13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework_13.models.Book;
import ru.otus.homework_13.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тестирование CommentRepoJpa")
@DataJpaTest
class CommentRepositoryTest {

    private static final int EXPECTED_COMMENTS_COUNT_TOTAL = 4;
    private static final int EXPECTED_COMMENTS_COUNT_FOR_BOOK = 2;
    private static final long EXISTING_COMMENT_ID = 1L;
    private static final String EXISTING_COMMENT_TEXT = "Хорошо!";
    private static final long EXISTING_BOOK_ID = 1L;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("method count")
    @Test
    void shouldReturnCommentCount() {
        long actualResult = commentRepository.count();
        assertThat(actualResult).isEqualTo(EXPECTED_COMMENTS_COUNT_TOTAL);
    }

    @DisplayName("method save")
    @Test
    void shouldInsertComment() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedResult = new Comment(null, "Текст нового комментария", book);
        commentRepository.save(expectedResult);
        Comment actualResult = commentRepository.getById(expectedResult.getId());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method save(update)")
    @Test
    void shouldUpdateComment() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedResult = new Comment(null, "Текст обновленного комментария", book);
        commentRepository.save(expectedResult);
        Comment actualComment = commentRepository.getById(expectedResult.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnCommentById() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedComment = new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_TEXT, book);
        Comment actualComment = commentRepository.getById(expectedComment.getId());
        assertEquals(expectedComment.getId(), actualComment.getId());
        assertEquals(expectedComment.getText(), actualComment.getText());
        assertEquals(expectedComment.getBook(), actualComment.getBook());
    }

    @DisplayName("method getAllByBook")
    @Test
    void shouldReturnCommentsByBookWithExpectedOnes() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedComment = new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_TEXT, book);
        List<Comment> actualCommentList = commentRepository.findByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentList).contains(expectedComment);
        assertThat(actualCommentList).isNotNull().hasSize(EXPECTED_COMMENTS_COUNT_FOR_BOOK)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getText().equals(""))
                .allMatch(s -> s.getBook().getId() > 0);
    }

    @DisplayName("method delete")
    @Test
    void shouldDeleteComment() {
        Comment comment = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID);
        commentRepository.delete(comment);
        assertNull(testEntityManager.find(Comment.class, EXISTING_COMMENT_ID));
    }

}