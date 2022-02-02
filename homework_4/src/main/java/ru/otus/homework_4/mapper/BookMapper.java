package ru.otus.homework_4.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework_4.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        long author_id = resultSet.getLong("author_id");
        long genre_id = resultSet.getLong("genre_id");
        return new Book(id, title, description, author_id, genre_id);
    }
}
