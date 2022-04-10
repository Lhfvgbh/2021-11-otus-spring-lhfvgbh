package ru.otus.homework_10.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor()
@Data
@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PEN_NAME", unique = true)
    private String penName;
}
