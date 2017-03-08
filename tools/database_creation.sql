-- User's table
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(30), -- arbitrary size
    alias VARCHAR(30) UNIQUE, -- abitrary size
    email VARCHAR(256) UNIQUE, -- 256 character max (RFC 2821)
    passwdHash VARCHAR(60) -- Bcrypt use 4 (hashing format) + 2 (cost parameter) + 22 (salt) + 31 (encrypted ouput) = 60 characters
);
