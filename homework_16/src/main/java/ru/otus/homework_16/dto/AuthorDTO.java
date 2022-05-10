package ru.otus.homework_16.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework_16.models.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private long id;

    @NotBlank(message = "{author-should-not-be-blank}")
    @Size(min = 3, max = 80, message = "{author-should-be-in-size-range-3-80}")
    private String name;

    public AuthorDTO(String name) {
        this.name = name;
    }

    public Author toDomainObject() {
        return new Author(this.id, this.name);
    }

    public static AuthorDTO fromDomainObject(Author author) {
        return new AuthorDTO(author.getId(), author.getPenName());
    }

}
