package ru.otus.homework_4.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", author.getId());
        params.put("name", author.getName());
        jdbc.update("insert into authors (id, name) values (:id , :name)", params);
    }

    @Override
    public Author getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select id, name from authors where id = :id",
                params, new AuthorMapper());
    }

    @Override
    public Author getByName(String name) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("name", name);
        return jdbc.queryForObject("select id, name from authors where name = :name",
                params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name from authors", new AuthorMapper());
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
        params.put("name", author.getName());
        jdbc.update("update authors set name = :name where id = :id", params);
    }
}
