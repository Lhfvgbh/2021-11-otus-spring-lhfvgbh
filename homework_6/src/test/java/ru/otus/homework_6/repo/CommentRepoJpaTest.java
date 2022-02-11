package ru.otus.homework_6.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework_6.models.Book;
import ru.otus.homework_6.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тестирование CommentRepoJpa")
@DataJpaTest
@Import(CommentRepoJpa.class)
class CommentRepoJpaTest {

    private static final int EXPECTED_COMMENTS_COUNT_TOTAL = 4;
    private static final int EXPECTED_COMMENTS_COUNT_FOR_BOOK = 2;
    private static final long EXISTING_COMMENT_ID = 1L;
    private static final String EXISTING_COMMENT_TEXT = "Хорошо!";
    private static final long EXISTING_BOOK_ID = 1L;

    @Autowired
    private CommentRepoJpa commentRepoJpa;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("method count")
    @Test
    void shouldReturnCommentCount() {
        long actualResult = commentRepoJpa.count();
        assertThat(actualResult).isEqualTo(EXPECTED_COMMENTS_COUNT_TOTAL);
    }

    @DisplayName("method save")
    @Test
    void shouldInsertComment() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedResult = new Comment(null, "Текст нового комментария", book);
        commentRepoJpa.save(expectedResult);
        Comment actualResult = commentRepoJpa.getById(expectedResult.getId()).orElse(new Comment());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method save(update)")
    @Test
    void shouldUpdateComment() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedResult = new Comment(null, "Текст обновленного комментария", book);
        commentRepoJpa.save(expectedResult);
        Comment actualComment = commentRepoJpa.getById(expectedResult.getId()).orElse(new Comment());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnCommentById() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedComment = new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_TEXT, book);
        Comment actualComment = commentRepoJpa.getById(expectedComment.getId()).orElse(new Comment());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("method getAllByBook")
    @Test
    void shouldReturnCommentsByBookWithExpectedOnes() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Comment expectedComment = new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_TEXT, book);
        List<Comment> actualCommentList = commentRepoJpa.getAllByBook(EXISTING_BOOK_ID);
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
        commentRepoJpa.delete(comment);
        assertNull(testEntityManager.find(Comment.class, EXISTING_COMMENT_ID));
    }

}