package ru.otus.homework_6.repo;

import org.springframework.stereotype.Repository;
import ru.otus.homework_6.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepoJpa implements AuthorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
    }

    @Override
    public Author save(Author author) {
        Long id = author.getId();
        if (id == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author getByName(String penName) {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.penName= :penName", Author.class);
        query.setParameter("penName", penName);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void delete(Author author) {
        entityManager.remove(author);
    }
}
