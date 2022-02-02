package ru.otus.homework_4.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework_4.domain.Book;
import ru.otus.homework_4.mapper.BookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        final Map<String, Object> params = getParamsFromBook(book);
        jdbc.update("insert into books (id, title, description, author_id, genre_id) " +
                "values (:id, :title, :description, :author_id, :genre_id)", params);
    }

    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select id, title, description, genre_id, author_id from books" +
                " where id = :id", params, new BookMapper());
    }

    @Override
    public Book getByTitle(String title) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", title);
        return jdbc.queryForObject("select id, title, description, genre_id, author_id from books" +
                " where title = :title", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, title, description, genre_id, author_id  from books", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public void update(Book book) {
        final Map<String, Object> params = getParamsFromBook(book);
        jdbc.update("update books set title = :title, description = :description," +
                " genre_id = :genre_id, author_id = :author_id where id = :id", params);
    }

    private Map<String, Object> getParamsFromBook(Book book) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        params.put("genre_id", book.getGenreId());
        params.put("author_id", book.getAuthorId());
        return params;
    }
}
