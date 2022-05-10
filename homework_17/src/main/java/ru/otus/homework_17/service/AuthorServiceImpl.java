package ru.otus.homework_17.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_17.models.Author;
import ru.otus.homework_17.repository.AuthorRepository;

import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author addNewAuthor(String name) {
        log.info("Adding new author with name: " + name);
        return authorRepository.save(new Author(-1L, name));
    }

    @Override
    @Transactional
    public Author addNewAuthor(Author author) {
        log.info("Adding new author with name: " + author.getPenName());
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author editAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthor(long authorNumber) {
        return authorRepository.getById(authorNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String authorName) {
        return authorRepository.findByPenName(authorName).orElse(new Author());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        Author author = authorRepository.getById(id);
        if (author.getId() > 0) {
            authorRepository.delete(author);
            log.info("Deleting author with id: " + id);
        }
    }
}
