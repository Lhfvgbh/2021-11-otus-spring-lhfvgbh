DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255) UNIQUE
);
CREATE TABLE BOOKS
(
    ID          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    TITLE       VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    AUTHOR_ID   BIGINT,
    GENRE_ID    BIGINT
);
CREATE TABLE GENRES
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255) UNIQUE
);
