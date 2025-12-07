--liquibase formatted sql

--changeset ashch:create-table-structure_division
CREATE TABLE structure_division(
    id SERIAL PRIMARY KEY,
    parent_id INTEGER REFERENCES structure_division (id) ON DELETE CASCADE,
    type_structure VARCHAR(32) NOT NULL,
    name VARCHAR(100) NOT NULL,
    UNIQUE(parent_id, name)
);
--rollback DROP TABLE structure_division

--changeset ashch:create-table-room
CREATE TABLE room(
    id SERIAL PRIMARY KEY,
    corps VARCHAR NOT NULL,
    room_number INTEGER NOT NULL,
    seats_value INTEGER NOT NULL,
    province_id INTEGER REFERENCES structure_division (id),
    UNIQUE(corps, room_number)
);
--rollback DROP TABLE room

--changeset ashch:create-table-users
CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    email VARCHAR(100) UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    role VARCHAR(32) NOT NULL,
    type VARCHAR(32),
    arrival_date TIMESTAMP,
    departure_date TIMESTAMP,
    organization VARCHAR(128),
    salary INTEGER,
    job_position VARCHAR(32),
    room_id INTEGER REFERENCES room (id) ON DELETE SET NULL,
    structure_division_id INTEGER REFERENCES structure_division (id)
);
--rollback DROP TABLE users

--changeset ashch:create-table-personal_information
CREATE TABLE personal_information(
    id BIGSERIAL PRIMARY KEY,
    lastname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100),
    passport_data VARCHAR(30) NOT NULL,
    address VARCHAR(255) NOT NULL,
    birth_certificate VARCHAR(100) NOT NULL,
    birth_date VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users (id)
);
--rollback DROP TABLE personal_information

--changeset ashch:create-table-event
CREATE TABLE event(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE,
    category VARCHAR(32) NOT NULL
);
--rollback DROP TABLE role

--changeset ashch:create-table-schedule
CREATE TABLE schedule(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (id),
    user_created_id INTEGER REFERENCES users (id) NOT NULL,
    event_id INTEGER REFERENCES event (id),
    structure_division_id INTEGER REFERENCES structure_division (id),
    date_time TIMESTAMP,
    status VARCHAR(32),
    action VARCHAR(128)
);
--rollback DROP TABLE schedule

--changeset ashch:create-table-excursion
CREATE TABLE excursion(
    id SERIAL PRIMARY KEY,
    structure_division_id INTEGER REFERENCES structure_division (id) ON DELETE CASCADE,
    service VARCHAR(64) NOT NULL UNIQUE,
    price INTEGER NOT NULL,
    duration BIGINT
);
--rollback DROP TABLE structure_division

--changeset ashch:create-table-image
CREATE TABLE image(
    id BIGSERIAL PRIMARY KEY,
    path VARCHAR(100) NOT NULL,
    user_id BIGINT REFERENCES users (id)
);
--rollback DROP TABLE image

--changeset ashch:create-table-user_excursion_purchase
CREATE TABLE user_excursion(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    excursion_id INTEGER NOT NULL REFERENCES excursion(id),
    purchase_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    purchase_price DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED',
    payment_method VARCHAR(50),
    payment_reference VARCHAR(100),
    notes TEXT,
    version INTEGER NOT NULL DEFAULT 0,

    CHECK (purchase_price > 0),
    CHECK (payment_status IN ('CONFIRMED', 'CANCELLED', 'COMPLETED', 'REFUNDED', 'PENDING')),
    CHECK (payment_method IN ('CARD', 'CASH', 'ONLINE'))
)
--rollback DROP TABLE user_excursion