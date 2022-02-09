package ru.otus.homework_4.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.homework_4.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("pen_name");
        return new Author(id, name);
    }
}
