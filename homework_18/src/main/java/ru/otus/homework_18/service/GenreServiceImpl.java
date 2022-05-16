package ru.otus.homework_18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework_18.models.Genre;
import ru.otus.homework_18.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand
    public Genre getGenreById(long genreId) {
        return genreRepository.findById(genreId).orElse(new Genre());
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
