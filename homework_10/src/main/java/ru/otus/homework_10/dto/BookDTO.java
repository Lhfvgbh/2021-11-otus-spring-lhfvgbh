package ru.otus.homework_10.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework_10.models.Author;
import ru.otus.homework_10.models.Book;
import ru.otus.homework_10.models.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private long id;

    @NotBlank(message = "{title-should-not-be-blank}")
    @Size(min = 3, max = 80, message = "{title-should-be-in-size-range-3-80}")
    private String title;
    private String description;
    private Long authorId;
    private Long genreId;

    public Book toDomainObject(Author author, Genre genre) {
        return new Book(id, title, description, author, genre);
    }

    public static BookDTO fromDomainObject(Book book) {
        return new BookDTO(book.getId(), book.getTitle(),
                book.getDescription(), book.getAuthor().getId(), book.getGenre().getId());
    }

}
