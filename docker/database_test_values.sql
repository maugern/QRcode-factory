-- Insertion fake data script to test the app
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (0, 'Homer Simpson','homer','homer@simpson.com','$2y$10$ayzQ.6lOmS265diM3Zb2kezgCegNAw2iQynAys3pgS9FyKjDTJ9om');
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (1, 'Dark Vador','annie','vador@start.com','$2y$10$zy5SpQ9qTDymfvgvDmXBce6.u9AY8UYWPI87TOaXNaPBn8.C7AjY.');
INSERT INTO users (id, name, alias, email, passwdHash) VALUES (2, 'Jim Raynor','jimmy','jim@raynor.com','$2y$10$eueP.xE8SlC7qu9ed6VtHu1ueQjLPsYfpWs2xSzWwosgAl75yjDTO');

INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='homer'),'ROLE_USER');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='homer'),'ROLE_ADMIN');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='annie'),'ROLE_USER');
INSERT INTO user_roles VALUES ((select id FROM users WHERE alias ='jimmy'),'ROLE_USER');
