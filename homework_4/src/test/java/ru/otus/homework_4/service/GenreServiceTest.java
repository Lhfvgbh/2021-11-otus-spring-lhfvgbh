package ru.otus.homework_4.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homework_4.dao.GenreDao;
import ru.otus.homework_4.domain.Genre;

import static org.mockito.Mockito.when;

@DisplayName("Тестирование GenreService")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Slf4j
public class GenreServiceTest {
    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreDao genreDao;

    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Сказка";

    @BeforeEach
    private void setupMock() {
        Genre genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        when(genreDao.getById(EXISTING_GENRE_ID)).thenReturn(genre);
    }

    @Test
    @DisplayName("Get genre from the library by ID")
    void shouldReturnGenreById() {
        Genre expectedResult = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualResult = genreService.getGenreById(expectedResult.getId());
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
