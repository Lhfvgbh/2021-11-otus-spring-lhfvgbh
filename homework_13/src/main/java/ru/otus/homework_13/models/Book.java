package ru.otus.homework_13.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(name = "book-author-genre-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false, targetEntity = Author.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Author author;

    @ManyToOne(optional = false, targetEntity = Genre.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Genre genre;
}
