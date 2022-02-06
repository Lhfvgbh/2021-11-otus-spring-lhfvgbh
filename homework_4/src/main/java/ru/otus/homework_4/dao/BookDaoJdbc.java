package ru.otus.homework_4.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("description", book.getDescription());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books (title, description, author_id, genre_id) " +
                "values (:title, :description, :author_id, :genre_id)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select b.id, b.title, b.description, b.genre_id, b.author_id, a.pen_name, g.name " +
                "from books b " +
                "left join authors a on a.id = b.author_id " +
                "left join genres g on g.id = b.genre_id " +
                "where b.id = :id", params, new BookMapper());

        //return jdbc.queryForObject("select id, title, description, genre_id, author_id from books" +
                //" where id = :id", params, new BookMapper());
    }

    @Override
    public Book getByTitle(String title) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", title);
        return jdbc.queryForObject("select b.id, b.title, b.description, b.genre_id, b.author_id, a.pen_name, g.name " +
                "from books b " +
                "left join authors a on a.id = b.author_id " +
                "left join genres g on g.id = b.genre_id " +
                "where b.title = :title", params, new BookMapper());
        //return jdbc.queryForObject("select id, title, description, genre_id, author_id from books" +
                //" where title = :title", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.title, b.description, b.genre_id, b.author_id, a.pen_name, g.name " +
                "from books b " +
                "left join authors a on a.id = b.author_id " +
                "left join genres g on g.id = b.genre_id;", new BookMapper());
        //return jdbc.query("select id, title, description, genre_id, author_id from books", new BookMapper());
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
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        return params;
    }
}
