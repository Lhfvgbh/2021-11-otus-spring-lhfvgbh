INSERT INTO authors (`pen_name`)
VALUES ('Джоан Роулинг');
INSERT INTO authors (`pen_name`)
VALUES ('Ханс Кристиан Андерсен');

INSERT INTO genres (`name`)
VALUES ('Сказка');
INSERT INTO genres (`name`)
VALUES ('Фентези');

INSERT INTO books (`title`, `description`, `author_id`, `genre_id`)
VALUES ('Гарри Поттер и философский камень',
        'первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.', 1, 1);
INSERT INTO books (`title`, `description`, `author_id`, `genre_id`)
VALUES ('Гарри Поттер и Тайная комната',
        'второй роман в серии книг про юного волшебника Гарри Поттера, написанный Джоан Роулинг', 1, 1);
INSERT INTO books (`title`, `description`, `author_id`, `genre_id`)
VALUES ('Снежная королева', 'сказка Ханса Кристиана Андерсена в 7 главах', 2, 2);

INSERT INTO comments (`text`, `book_id`)
VALUES ('Хорошо!', 1);
INSERT INTO comments (`text`, `book_id`)
VALUES ('Очень хорошо!', 1);
INSERT INTO comments (`text`, `book_id`)
VALUES ('Хорошо!', 2);
INSERT INTO comments (`text`, `book_id`)
VALUES ('Хорошо!', 3);

--password
INSERT INTO users(`username`, `password`)
VALUES ('admin', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK'),
       ('user', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK');

INSERT INTO roles(`role_name`)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);
