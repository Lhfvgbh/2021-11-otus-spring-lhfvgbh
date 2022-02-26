package ru.otus.homework_6.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework_6.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тестирование AuthorRepoJpa")
@DataJpaTest
@Import(AuthorRepoJpa.class)
class AuthorRepoJpaTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Ханс Кристиан Андерсен";

    @Autowired
    private AuthorRepoJpa authorRepoJpa;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName("method count")
    @Test
    void shouldReturnAuthorCount() {
        long actualResult = authorRepoJpa.count();
        assertThat(actualResult).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("method save")
    @Test
    void shouldInsertAuthor() {
        Author expectedResult = new Author(null, "Джон Р. Р. Толкин");
        authorRepoJpa.save(expectedResult);
        Author actualResult = authorRepoJpa.getById(expectedResult.getId()).orElse(new Author());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method save(update)")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, "Ганс Христиан Андерсен");
        authorRepoJpa.save(expectedAuthor);
        Author actualAuthor = authorRepoJpa.getById(expectedAuthor.getId()).orElse(new Author());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepoJpa.getById(expectedAuthor.getId()).orElse(new Author());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getByName")
    @Test
    void shouldReturnAuthorByName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepoJpa.getByName(expectedAuthor.getPenName());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getAllAuthors")
    @Test
    void shouldReturnAuthorsListWithExpectedOnes() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualAuthorList = authorRepoJpa.getAll();
        assertThat(actualAuthorList).contains(expectedAuthor);
        assertThat(actualAuthorList).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getPenName().equals(""));
    }

    @DisplayName("method delete")
    @Test
    void shouldDeleteAuthor() {
        Author author = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        authorRepoJpa.delete(author);
        assertNull(testEntityManager.find(Author.class, EXISTING_AUTHOR_ID));
    }

}