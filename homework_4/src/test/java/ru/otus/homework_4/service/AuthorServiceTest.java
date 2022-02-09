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
import ru.otus.homework_4.dao.AuthorDao;
import ru.otus.homework_4.domain.Author;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Тестирование AuthorService")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Slf4j
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorDao authorDao;

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Ханс Кристиан Андерсен";

    @BeforeEach
    private void setupMock() {
        Author author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        when(authorDao.getById(EXISTING_AUTHOR_ID)).thenReturn(author);
        when(authorDao.getByName(EXISTING_AUTHOR_NAME)).thenReturn(author);
        when(authorDao.getAll()).thenReturn(Arrays.asList(author, author));
    }


    @Test
    @DisplayName("Get author from the library by ID")
    void shouldReturnAuthorById() {
        Author expectedResult = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualResult = authorService.getAuthor(expectedResult.getId());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get author from the library by name")
    void shouldReturnAuthorByName() {
        Author expectedResult = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualResult = authorService.getAuthorByName(expectedResult.getName());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get all available authors represented in the library")
    void shouldReturnExpectedAuthorsList() {
        Author expectedResult = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        List<Author> actualResult = authorService.getAllAuthors();
        assertThat(actualResult.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualResult).contains(expectedResult);
    }
}
