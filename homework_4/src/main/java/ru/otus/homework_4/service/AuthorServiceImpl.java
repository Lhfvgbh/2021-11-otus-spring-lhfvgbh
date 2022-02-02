package ru.otus.homework_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework_4.dao.AuthorDao;
import ru.otus.homework_4.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void addNewAuthor(Author author) {
        authorDao.insert(author);
    }

    @Override
    public void deleteAuthor(long id) {
        authorDao.deleteById(id);
    }

    @Override
    public void editAuthor(Author author) {
        authorDao.update(author);
    }

    @Override
    public Author getAuthor(long authorNumber) {
        return authorDao.getById(authorNumber);
    }

    @Override
    public Author getAuthorByName(String authorName) {
        return authorDao.getByName(authorName);
    }

    @Override
    public int getTotalNumberOfAuthors() {
        return authorDao.count();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }
}
