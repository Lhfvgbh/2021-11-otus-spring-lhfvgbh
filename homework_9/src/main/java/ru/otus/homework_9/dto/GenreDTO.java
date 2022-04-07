package ru.otus.homework_9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework_9.models.Genre;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    //@Size(min = 5, max = 50, message = "{genre-field-should-has-expected-size}")
    private String name;

    public GenreDTO(String name) {
        this.name = name;
    }

    public Genre toDomainObject() {
        return new Genre(this.id, this.name);
    }

    public static GenreDTO fromDomainObject(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }

}
