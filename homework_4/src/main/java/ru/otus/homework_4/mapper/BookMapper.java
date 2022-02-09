package ru.otus.homework_4.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.domain.Book;
import ru.otus.homework_4.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        Author author = new Author(resultSet.getLong("author_id"),
                resultSet.getString("pen_name"));
        Genre genre = new Genre(resultSet.getLong("genre_id"),
                resultSet.getString("name"));
        return new Book(id, title, description, author, genre);
    }
}
