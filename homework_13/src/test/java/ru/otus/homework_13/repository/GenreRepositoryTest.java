package ru.otus.homework_13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.homework_13.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование GenreRepoJpa")
@DataJpaTest
class GenreRepositoryTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("method save")
    @Test
    void shouldInsertGenre() {
        Genre expectedResult = new Genre(null, "Роман");
        genreRepository.save(expectedResult);
        Genre actualResult = genreRepository.getById(expectedResult.getId());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreRepository.getById(expectedGenre.getId());
        assertEquals(expectedGenre.getId(), actualGenre.getId());
        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @DisplayName("method getAllGenres")
    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList).contains(expectedGenre);
        assertThat(actualGenreList).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));

    }

}