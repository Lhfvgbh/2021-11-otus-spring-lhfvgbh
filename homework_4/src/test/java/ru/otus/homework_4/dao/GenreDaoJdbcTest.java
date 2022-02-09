package ru.otus.homework_4.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework_4.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тестирование GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("method count")
    @Test
    void shouldReturnGenreCount() {
        int actualResult = genreDao.count();
        assertThat(actualResult).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("method insert")
    @Test
    void shouldInsertGenre() {
        Genre expectedResult = new Genre(3, "Роман");
        genreDao.insert(expectedResult);
        Genre actualResult = genreDao.getById(expectedResult.getId());
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @DisplayName("method getById")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("method getAllGenres")
    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList.size()).isEqualTo(2);
        assertThat(actualGenreList).contains(expectedGenre);
    }

    @DisplayName("method update")
    @Test
    void shouldUpdateGenre() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, "Сказки");
        genreDao.update(expectedGenre);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("method deleteById")
    @Test
    void shouldDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID))

                .doesNotThrowAnyException();
        genreDao.deleteById(EXISTING_GENRE_ID);

        assertThatThrownBy(() -> genreDao.getById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}