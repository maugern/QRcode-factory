-- User's table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL,
    name VARCHAR(30), -- arbitrary size
    alias VARCHAR(30) UNIQUE, -- must be unique because used for login
    email VARCHAR(256) UNIQUE, -- 256 character max (RFC 2821)
    passwdHash VARCHAR(60) CHECK (length(passwdHash) > 58), -- Bcrypt use 3 or 4 (hashing format id) + 2 (cost parameter) + 22 (salt) + 31 (encrypted ouput) = 59 or 60 characters
    CONSTRAINT "PKey" PRIMARY KEY (id)
);
