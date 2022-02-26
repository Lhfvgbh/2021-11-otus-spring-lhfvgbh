package ru.otus.homework_7.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework_7.models.Comment;
import ru.otus.homework_7.service.BookService;
import ru.otus.homework_7.service.CommentService;
import ru.otus.homework_7.service.OutputService;

import java.util.Collections;

@ShellComponent
public class CommentShellService {
    private final BookService bookService;
    private final CommentService commentService;
    private final OutputService outputService;

    @Autowired
    public CommentShellService(BookService bookService, CommentService commentService, OutputService outputService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.outputService = outputService;
    }

    @ShellMethod(key = "countAllBookComments", value = "Print total number of comments")
    public void countAllBookComments() {
        outputService.printOne(commentService.getTotalNumberOfComments());
    }

    @ShellMethod(key = "getAllCommentsForBook", value = "Print all available book genres")
    public void getAllCommentsForBook(@ShellOption({"bookId", "b"}) long book_id) {
        outputService.printList(Collections.singletonList(commentService.getAllCommentsForBook(book_id)));
    }

    @ShellMethod(key = "addCommentForBook", value = "Add new comment for the book")
    public void addCommentForBook(@ShellOption({"bookId", "b"}) long book_id,
                                  @ShellOption({"text", "t"}) String text) {
        commentService.addNewComment(new Comment(-1L, text, bookService.getBook(book_id)));
    }
}
