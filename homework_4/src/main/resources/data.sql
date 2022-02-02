insert into authors (id, `name`)
values (1, 'Джоан Роулинг');
insert into authors (id, `name`)
values (2, 'Ханс Кристиан Андерсен');
insert into genres (id, `name`)
values (1, 'Сказка');
insert into genres (id, `name`)
values (2, 'Фентези');
insert into books (id, `title`, `description`, `author_id`, `genre_id`)
values (1, 'Гарри Поттер и философский камень',
        'первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг.', 2, 1);
insert into books (id, `title`, `description`, `author_id`, `genre_id`)
values (2, 'Гарри Поттер и Тайная комната',
        'второй роман в серии книг про юного волшебника Гарри Поттера, написанный Джоан Роулинг', 2, 1);
insert into books (id, `title`, `description`, `author_id`, `genre_id`)
values (3, 'Снежная королева', 'сказка Ханса Кристиана Андерсена в 7 главах', 1, 2);
