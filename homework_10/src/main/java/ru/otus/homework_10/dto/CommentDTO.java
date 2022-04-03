package ru.otus.homework_10.dto;

import lombok.*;
import ru.otus.homework_10.models.Book;
import ru.otus.homework_10.models.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@EqualsAndHashCode(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private long id;

    private Long bookId;

    @NotBlank(message = "{comment-should-not-be-blank}")
    @Size(min = 10, max = 255, message = "{comment-should-be-in-size-range-10-255}")
    private String text;


    public Comment toDomainObject(Book book) {
        return new Comment(this.id, this.text, book);
    }

    public static CommentDTO fromDomainObject(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getBook().getId(), comment.getText());
    }

}
