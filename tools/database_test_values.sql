-- Insertion fake data script to test the app
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (DEFAULT, 'Homer Simpson','homer','homer@simpson.com','$2y$10$kqqa/.sHypFqQC4oqdY3me/Hz0QrvCDLEb2jwKs/9KKufp7nDJ6ty');
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (DEFAULT, 'Dark Vador','annie','vador@start.com','$2y$10$ZgBPumOQKcsywxSjWXCt3uaqDy9szB/lC5pP1ZhKoU/9QrfuoAL8y');
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (DEFAULT, 'Jim Raynor','jimmy','jim@raynor.com','$2y$10$eueP.xE8SlC7qu9ed6VtHu1ueQjLPsYfpWs2xSzWwosgAl75yjDTO');

INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='homer'),'ROLE_USER');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='homer'),'ROLE_ADMIN');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='annie'),'ROLE_USER');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='jimmy'),'ROLE_USER');
