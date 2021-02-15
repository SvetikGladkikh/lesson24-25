DROP TABLE IF EXISTS TBL_BOOKS;
DROP TABLE IF EXISTS TBL_AUTHOR;
DROP TABLE IF EXISTS TBL_GENRE;

CREATE TABLE TBL_AUTHOR (
                            id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                            first_name VARCHAR(250) NOT NULL,
                            last_name VARCHAR(250) NOT NULL
);
CREATE TABLE TBL_GENRE (
                            id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                            genre VARCHAR(250) NOT NULL
);
CREATE TABLE TBL_BOOKS (
                           id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                           name VARCHAR(250) NOT NULL,
                           author_id BIGINT,
                           FOREIGN KEY(author_id) REFERENCES TBL_AUTHOR,
                           genre_id BIGINT,
                           FOREIGN KEY(genre_id) REFERENCES TBL_GENRE
);
