package ru.otus.homework_13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("")
    public String listBooksPage() {
        return "index";
    }
}
