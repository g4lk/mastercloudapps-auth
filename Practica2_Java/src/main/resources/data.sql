-- Saving books
INSERT INTO books (title, summary, author, publisher, publication_year) VALUES ("Book 1 title", "Book 1 summary","Book 1 author","Book 1 publisher",1999);
INSERT INTO books (title, summary, author, publisher, publication_year) VALUES ("Book 2 title", "Book 2 summary","Book 2 author","Book 2 publisher",2012);

-- saving users
INSERT INTO users (email, nick, password) VALUES('user1@email.es', 'user1', '$2y$12$YmvT8IkJzx0RcYf8zQMal.lXuhxZW/EJllzmbDi1TOpLjicbVjW8u');
INSERT INTO users (email, nick, password) VALUES('user2@email.es', 'user2', '$2y$12$YmvT8IkJzx0RcYf8zQMal.lXuhxZW/EJllzmbDi1TOpLjicbVjW8u');

-- saving comments
INSERT INTO comments (comment, score,  book_id, user_id) VALUES ("Book 2 comment 1", 3, 2, 1);
INSERT INTO comments (comment, score, book_id, user_id) VALUES ("Book 2 comment 2", 4, 2, 1);