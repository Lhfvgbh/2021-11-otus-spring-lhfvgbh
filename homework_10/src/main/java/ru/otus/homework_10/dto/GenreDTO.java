package ru.otus.homework_10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework_10.models.Genre;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String name;

    public Genre toDomainObject() {
        return new Genre(id, name);
    }

    public static GenreDTO fromDomainObject(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName());
    }

}
