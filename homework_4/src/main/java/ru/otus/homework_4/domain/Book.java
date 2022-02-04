package ru.otus.homework_4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private long id;
    private String title;
    private String description;
    private long authorId;
    private long genreId;
}
