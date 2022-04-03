package ru.otus.homework_10.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework_10.dto.AuthorDTO;
import ru.otus.homework_10.models.Author;
import ru.otus.homework_10.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

    @GetMapping("/api/v1/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
