package ru.otus.homework_6.repo;

import org.springframework.stereotype.Repository;
import ru.otus.homework_6.models.Author;
import ru.otus.homework_6.models.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepoJpa implements CommentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from Comment c", Long.class).getSingleResult();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAllByBook(long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c from Comment c join c.book b join fetch c.book where b.id=:bookId", Comment.class);
        query.setParameter("bookId", bookId);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("comment-book-graph"));
        return query.getResultList();
    }


    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
