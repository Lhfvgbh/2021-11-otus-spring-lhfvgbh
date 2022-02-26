package ru.otus.homework_6.repo;

import org.springframework.stereotype.Repository;
import ru.otus.homework_6.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepoJpa implements BookRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public Optional<Book> getById(long id) {
        //Book book = entityManager.find(Book.class, id);

        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public List<Book> getByTitle(String title) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.title= :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("book-author-genre-graph"));
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
