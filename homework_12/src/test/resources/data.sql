insert into authors (`pen_name`)
values ('Ханс Кристиан Андерсен');
insert into authors (`pen_name`)
values ('Джоан Роулинг');
insert into genres (`name`)
values ('Сказка');
insert into genres (`name`)
values ('Фентези');
insert into books (`title`, `description`, `author_id`, `genre_id`)
values ('Гарри Поттер и философский камень',
        'первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.', 2, 1);
insert into books (`title`, `description`, `author_id`, `genre_id`)
values ('Гарри Поттер и Тайная комната',
        'второй роман в серии книг про юного волшебника Гарри Поттера, написанный Джоан Роулинг', 2, 1);
insert into books (`title`, `description`, `author_id`, `genre_id`)
values ('Снежная королева', 'сказка Ханса Кристиана Андерсена в 7 главах', 1, 2);
insert into comments (`text`, `book_id`)
values ('Хорошо!', 1);
insert into comments (`text`, `book_id`)
values ('Очень хорошо!', 1);
insert into comments (`text`, `book_id`)
values ('Хорошо!', 2);
insert into comments (`text`, `book_id`)
values ('Хорошо!', 3);

INSERT INTO users(`username`, `password`)
VALUES ('admin', '{bcrypt}$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK'),
       ('user', '{noop}password');

INSERT INTO roles(`role_name`)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);