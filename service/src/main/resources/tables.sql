CREATE TABLE subdivision
(
    id SERIAL PRIMARY KEY,
    subdivision VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE service
(
    id SERIAL PRIMARY KEY,
    subdivision_id INTEGER REFERENCES subdivision (id),
    service VARCHAR(100) NOT NULL,
    price VARCHAR(100),
    duration TIMESTAMP
);

CREATE TABLE priority
(
    id SERIAL PRIMARY KEY,
    priority VARCHAR(100) NOT NULL UNIQUE,
    comment VARCHAR(255)
);

CREATE TABLE post
(
    id SERIAL PRIMARY KEY,
    post VARCHAR(100) NOT NULL UNIQUE,
    priority_id INTEGER REFERENCES priority (id)
);

CREATE TABLE role
(
    id SERIAL PRIMARY KEY,
    role VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE room
(
    id SERIAL PRIMARY KEY,
    corps VARCHAR NOT NULL,
    room_number INTEGER,
    seats_value INTEGER
);

CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    post_id INTEGER NOT NULL REFERENCES post (id),
    subdivision_id INTEGER NOT NULL REFERENCES subdivision (id),
    role_id INTEGER NOT NULL REFERENCES role (id),
    room_id INTEGER NOT NULL REFERENCES room (id),
    lastname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    date_registration TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE image
(
    id BIGSERIAL PRIMARY KEY,
    path VARCHAR(100) NOT NULL,
    user_id BIGINT REFERENCES users (id)
);

CREATE TABLE schedule
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users (id),
    date_time TIMESTAMP,
    action VARCHAR(255),
    status VARCHAR(20)
);

CREATE TABLE chat
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    date_chat TIMESTAMP
);

CREATE TABLE user_chat
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id),
    chat_id BIGINT NOT NULL REFERENCES chat (id),
    date_time TIMESTAMP,
    status VARCHAR(100)
);

CREATE TABLE personal_information
(
    id BIGSERIAL PRIMARY KEY,
    passport_data VARCHAR(30) NOT NULL,
    address VARCHAR(255) NOT NULL,
    birth_certificate VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users (id)
)