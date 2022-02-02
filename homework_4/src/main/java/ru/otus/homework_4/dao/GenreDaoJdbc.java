package ru.otus.homework_4.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework_4.domain.Genre;
import ru.otus.homework_4.mapper.GenreMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("insert into genres (id, name) values (:id, :name)", params);
    }

    @Override
    public Genre getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select id, name from genres where id = :id",
                params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbc.update("delete from genres where id = :id", params);
    }

    @Override
    public void update(Genre genre) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("update genres set name = :name where id = :id", params);
    }
}
