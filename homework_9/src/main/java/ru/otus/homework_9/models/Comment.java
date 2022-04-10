package ru.otus.homework_9.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMMENTS")
@NamedEntityGraph(name = "comment-book-graph",
        attributeNodes = {@NamedAttributeNode(value = "book", subgraph = "book-subgraph")},
        subgraphs = {@NamedSubgraph(name = "book-subgraph",
                attributeNodes = {@NamedAttributeNode("genre"), @NamedAttributeNode("author")})})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
