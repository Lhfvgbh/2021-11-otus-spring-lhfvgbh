package ru.otus.homework_6.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_6.repo.AuthorRepo;
import ru.otus.homework_6.models.Author;

import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    @Transactional
    public Author addNewAuthor(String name) {
        log.info("Adding new author with name: " + name);
        return authorRepo.save(new Author(-1L, name));
    }

    @Override
    @Transactional
    public Author addNewAuthor(Author author) {
        log.info("Adding new author with name: " + author.getPenName());
        return authorRepo.save(author);
    }

    @Override
    @Transactional
    public Author editAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthor(long authorNumber) {
        return authorRepo.getById(authorNumber).orElse(new Author());
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String authorName) {
        return authorRepo.getByName(authorName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepo.getAll();
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        Author author = authorRepo.getById(id).orElse(null);
        if (author != null) {
            authorRepo.delete(author);
            log.info("Deleting author with id: " + id);
        }
    }
}
