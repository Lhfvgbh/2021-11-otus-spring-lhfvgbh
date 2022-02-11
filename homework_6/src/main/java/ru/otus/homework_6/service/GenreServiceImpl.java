package ru.otus.homework_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_6.repo.GenreRepo;
import ru.otus.homework_6.models.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    @Autowired
    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(long genreId) {
        return genreRepo.getById(genreId).orElse(new Genre());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepo.getAll();
    }

    @Override
    @Transactional
    public Genre addGenre(Genre genre) {
        return genreRepo.save(genre);
    }
}
