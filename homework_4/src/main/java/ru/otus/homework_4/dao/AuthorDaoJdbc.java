package ru.otus.homework_4.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework_4.domain.Author;
import ru.otus.homework_4.mapper.AuthorMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pen_name", author.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors (`pen_name`) values (:pen_name)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Author getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select id, pen_name from authors where id = :id",
                params, new AuthorMapper());
    }

    @Override
    public Author getByName(String name) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("pen_name", name);
        return jdbc.queryForObject("select id, pen_name from authors where pen_name = :pen_name",
                params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, pen_name from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }

    @Override
    public void update(Author author) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", author.getId());
        params.put("pen_name", author.getName());
        jdbc.update("update authors set pen_name = :pen_name where id = :id", params);
    }
}
