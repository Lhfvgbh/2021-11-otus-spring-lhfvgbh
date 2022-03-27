package ru.otus.homework_10.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework_10.dto.GenreDTO;
import ru.otus.homework_10.models.Genre;
import ru.otus.homework_10.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public List<GenreDTO> getAllAuthors() {
        List<GenreDTO> genres = new ArrayList<>();
        for (Genre genre : genreService.getAllGenres()) {
            genres.add(GenreDTO.fromDomainObject(genre));
        }
        return genres;
    }
}
