--liquibase formatted sql


--changeset ashch:create-table-structure_division
CREATE TABLE structure_division
(
    id SERIAL PRIMARY KEY,
    parent_id INTEGER,
    type_structure VARCHAR(32) NOT NULL,
    name VARCHAR(100) NOT NULL,
    UNIQUE(parent_id, name)
);
--rollback DROP TABLE structure_division

--changeset ashch:create-table-room
CREATE TABLE room
(
    id SERIAL PRIMARY KEY,
    corps VARCHAR NOT NULL,
    room_number INTEGER,
    seats_value INTEGER,
    province_id INTEGER REFERENCES structure_division (id)
);
--rollback DROP TABLE room

--changeset ashch:create-table-users
CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100),
    password VARCHAR(30) NOT NULL,
    date_registration TIMESTAMP NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL,
    role VARCHAR(32) NOT NULL,
    salary INTEGER,
    job_position VARCHAR(32),
    room_id INTEGER NOT NULL REFERENCES room (id),
    structure_division_id INTEGER NOT NULL REFERENCES structure_division (id)
);
--rollback DROP TABLE users

--changeset ashch:create-table-personal_information
CREATE TABLE personal_information
(
    id BIGSERIAL PRIMARY KEY,
    passport_data VARCHAR(30) NOT NULL,
    address VARCHAR(255) NOT NULL,
    birth_certificate VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users (id)
);
--rollback DROP TABLE personal_information

--changeset ashch:create-table-event
CREATE TABLE event
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE,
    category VARCHAR(32) NOT NULL
);
--rollback DROP TABLE role

--changeset ashch:create-table-schedule
CREATE TABLE schedule
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users (id),
    event_id INTEGER NOT NULL REFERENCES event (id),
    structure_division_id INTEGER REFERENCES structure_division (id),
    date_time TIMESTAMP,
    status VARCHAR(32),
    action VARCHAR(128)
);
--rollback DROP TABLE schedule

--changeset ashch:create-table-extra_service
CREATE TABLE extra_service
(
    id SERIAL PRIMARY KEY,
    structure_division_id INTEGER REFERENCES structure_division (id),
    service VARCHAR(64) NOT NULL,
    price INTEGER,
    duration INTERVAL
);
--rollback DROP TABLE structure_division

--changeset ashch:create-table-image
CREATE TABLE image
(
    id BIGSERIAL PRIMARY KEY,
    path VARCHAR(100) NOT NULL,
    user_id BIGINT REFERENCES users (id)
);
--rollback DROP TABLE image