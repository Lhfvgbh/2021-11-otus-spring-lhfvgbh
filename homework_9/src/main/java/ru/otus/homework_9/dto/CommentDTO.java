package ru.otus.homework_9.dto;

import lombok.*;
import ru.otus.homework_9.models.Book;
import ru.otus.homework_9.models.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@EqualsAndHashCode(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private long id;

    //@ToString.Exclude
    private Book book;

    @NotBlank(message = "{comment-should-not-be-blank}")
    @Size(min = 10, max = 255, message = "{comment-should-be-in-size-range-10-255}")
    private String text;


    public Comment toDomainObject() {
        return new Comment(this.id, this.text, this.book);
    }

    public static CommentDTO fromDomainObject(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getBook(), comment.getText());
    }

}
