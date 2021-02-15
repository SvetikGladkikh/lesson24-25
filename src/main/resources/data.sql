INSERT INTO TBL_AUTHOR (first_name, last_name) VALUES
('Fredric', 'Backman'),
('Terry', 'Pratchett'),
('Mark', 'Levy');

INSERT INTO TBL_GENRE (genre) VALUES
('Realistic Fiction'),
('Fiction'),
('Comic fantasy'),
('Novel');

INSERT INTO TBL_BOOKS (name, author_id, genre_id) VALUES
('A Man Called Ove', 1, 1),
('My Grandmother Asked Me to Tell You She''s Sorry', 1, 2),
('Good Omens', 2, 3),
('If Only It Were True', 3, 4);
