DROP TABLE IF EXISTS user_role;
DROP TYPE IF EXISTS ROLE;
DROP TABLE IF EXISTS user;

-- User's table
CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY,
    name VARCHAR(40), -- arbitrary size
    alias VARCHAR(40) UNIQUE, -- must be unique because used for login
    email VARCHAR(254) UNIQUE, -- 254 character max (RFC 2821)
    passwdHash VARCHAR(60) NOT NULL -- Bcrypt use 3 or 4 (hashing format id) + 2 (cost parameter) + 22 (salt) + 31 (encrypted ouput) = 59 or 60 characters
);

CREATE TYPE ROLE AS ENUM ('ROLE_ADMIN', 'ROLE_USER');
CREATE TABLE IF NOT EXISTS user_role (
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    role ROLE
);

-- CREATE TABLE IF NOT EXISTS guestbook (id PRIMARY KEY,author INTEGER REFERENCES users(id) ON DELETE CASCADE,    creation TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,commentary VARCHAR(140));
