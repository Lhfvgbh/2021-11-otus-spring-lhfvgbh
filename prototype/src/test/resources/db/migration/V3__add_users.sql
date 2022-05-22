insert into users (id, name, password, email, is_active, role)
values (1, 'Admin', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK', 'admin@example.com', true, 'ADMIN');

insert into users (id, name, password, email, is_active, role)
values (2, 'Admin2', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK', 'admin2@example.com', true, 'ADMIN');

insert into users (id, name, password, email, is_active, role)
values (3, 'User1', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK', 'user1@example.com', true, 'CLIENT');

insert into users (id, name, password, email, is_active, role)
values (4, 'User2', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK', 'user2@example.com', true, 'CLIENT');

insert into users (id, name, password, email, is_active, role)
values (5, 'User3', '$2a$12$LmMd853HNoLn2M2VS3rFEu7n59ZR7QQSqlyi5iTWxjRS3YspG.OrK', 'user3@example.com', false, 'CLIENT');

ALTER SEQUENCE users_id_seq RESTART WITH 6;



