package ru.otus.homework_6.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_6.models.Comment;
import ru.otus.homework_6.service.BookService;
import ru.otus.homework_6.service.CommentService;

@ShellComponent
public class CommentShellService {
    private final BookService bookService;
    private final CommentService commentService;

    @Autowired
    public CommentShellService(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @ShellMethod(key = "countAllBookComments", value = "Print total number of comments")
    public void countAllBookComments() {
        System.out.println(commentService.getTotalNumberOfComments());
    }

    @ShellMethod(key = "getAllCommentsForBook", value = "Print all available book genres")
    public void getAllCommentsForBook(@ShellOption({"bookId", "b"}) long book_id) {
        for (Comment comment : commentService.getAllCommentsForBook(book_id)) {
            System.out.println(comment);
        }
    }

    @ShellMethod(key = "addCommentForBook", value = "Add new comment for the book")
    public void addCommentForBook(@ShellOption({"bookId", "b"}) long book_id,
                                  @ShellOption({"text", "t"}) String text) {
        commentService.addNewComment(new Comment(-1L, text, bookService.getBook(book_id)));
    }
}
