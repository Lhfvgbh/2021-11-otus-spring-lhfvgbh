insert into authors (`pen_name`)
values ('Ханс Кристиан Андерсен');
insert into authors (`pen_name`)
values ('Джоан Роулинг');
insert into genres (`name`)
values ('Фентези');
insert into genres (`name`)
values ('Сказка');
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