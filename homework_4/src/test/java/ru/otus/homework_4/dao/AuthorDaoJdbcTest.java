package ru.otus.homework_4.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework_4.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование AuthorDaoJdbc")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Ханс Кристиан Андерсен";

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("method count")
    @Test
    void shouldReturnAuthorCount() {
        int actualResult = authorDao.count();
        assertThat(actualResult).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("method insert")
    @Test
    void shouldInsertAuthor() {
        Author expectedResult = new Author(3, "Джон Р. Р. Толкин");
        authorDao.insert(expectedResult);
        Author actualResult = authorDao.getById(expectedResult.getId());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getByName")
    @Test
    void shouldReturnAuthorByTitle() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getByName(expectedAuthor.getName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getAllAuthors")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList.size()).isEqualTo(2);
        assertThat(actualAuthorList).contains(expectedAuthor);
    }

    @DisplayName("method update")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, "Ганс Христиан Андерсен");
        authorDao.update(expectedAuthor);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method deleteById")
    @Test
    void shouldDeleteAuthorById() {
        assertThatCode(() -> authorDao.getById(EXISTING_AUTHOR_ID))

                .doesNotThrowAnyException();
        authorDao.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}