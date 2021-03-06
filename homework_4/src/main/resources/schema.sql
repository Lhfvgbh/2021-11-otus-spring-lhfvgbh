DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    PEN_NAME VARCHAR(255) UNIQUE
);
CREATE TABLE GENRES
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) UNIQUE
);
CREATE TABLE BOOKS
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE       VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    AUTHOR_ID   BIGINT,
    GENRE_ID    BIGINT,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(ID) ON DELETE CASCADE,
    FOREIGN KEY (GENRE_ID) REFERENCES GENRES(ID) ON DELETE CASCADE
);
