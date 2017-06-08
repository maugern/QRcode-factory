CREATE TABLE role(
  id SERIAL PRIMARY KEY,
  name varchar(45) DEFAULT NULL
);

INSERT INTO role VALUES (1,'ROLE_USER');

--
-- Table structure for table `user`
--

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL
);

--
-- Table structure for table `user_role`
--

CREATE TABLE user_role (
  users_id integer REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
  role_id integer REFERENCES role(id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (users_id,role_id)
);

--
-- Table structure for table `qrcode`
--

CREATE TABLE IF NOT EXISTS qrcode (
    qrcode_id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    url VARCHAR(4000),
    hashid VARCHAR,
    salt VARCHAR
);
