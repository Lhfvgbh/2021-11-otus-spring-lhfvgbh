package ru.otus.homework_6.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework_6.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование GenreRepoJpa")
@DataJpaTest
@Import(GenreRepoJpa.class)
class GenreRepoJpaTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @Autowired
    private GenreRepoJpa genreRepoJpa;

    @DisplayName("method save")
    @Test
    void shouldInsertGenre() {
        Genre expectedResult = new Genre(null, "Роман");
        genreRepoJpa.save(expectedResult);
        Genre actualResult = genreRepoJpa.getById(expectedResult.getId()).orElse(new Genre());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreRepoJpa.getById(expectedGenre.getId()).orElse(new Genre());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("method getAllGenres")
    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreRepoJpa.getAll();
        assertThat(actualGenreList).contains(expectedGenre);
        assertThat(actualGenreList).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));

    }

}