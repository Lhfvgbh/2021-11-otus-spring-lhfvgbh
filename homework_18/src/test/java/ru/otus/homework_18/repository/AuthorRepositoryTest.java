package ru.otus.homework_18.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework_18.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тестирование AuthorRepoJpa")
@DataJpaTest
class AuthorRepositoryTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Ханс Кристиан Андерсен";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName("method count")
    @Test
    void shouldReturnAuthorCount() {
        long actualAuthors = authorRepository.count();
        assertThat(actualAuthors).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("method save")
    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(null, "Джон Р. Р. Толкин");
        authorRepository.save(expectedAuthor);
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(new Author());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method save(update)")
    @Test
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, "Ганс Христиан Андерсен");
        authorRepository.save(expectedAuthor);
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(new Author());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(new Author());
        assertEquals(expectedAuthor.getId(), actualAuthor.getId());
        assertEquals(expectedAuthor.getPenName(), actualAuthor.getPenName());
    }

    @DisplayName("method getByName")
    @Test
    void shouldReturnAuthorByName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorRepository.findByPenName(expectedAuthor.getPenName()).orElse(new Author());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("method getAllAuthors")
    @Test
    void shouldReturnAuthorsListWithExpectedOnes() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualAuthorList = authorRepository.findAll();
        assertThat(actualAuthorList).contains(expectedAuthor);
        assertThat(actualAuthorList).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getPenName().equals(""));
    }

    @DisplayName("method delete")
    @Test
    void shouldDeleteAuthor() {
        Author author = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        authorRepository.delete(author);
        assertNull(testEntityManager.find(Author.class, EXISTING_AUTHOR_ID));
    }

}