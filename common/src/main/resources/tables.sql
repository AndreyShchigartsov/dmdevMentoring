create table Users
(
    id PRIMARY KEY,
    lastname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    role JSON NOT NULL,
    active boolean,
    id_subdivision integer,
    date_registration timestamp
);

create table Roles
(
    id PRIMARY KEY,
    role VARCHAR(100) NOT NULL,
    priority integer NOT NULL,
);

create table Actions
(
    id PRIMARY KEY,
    actions VARCHAR(100) NOT NULL,
    min_priority integer NOT NULL,
    role_id integer NOT NULL,
);

create table Schedule
(
    id PRIMARY KEY,
    id_user long NOT NULL,
    date_time timestamp,
    id_measure VARCHAR(100) NOT NULL,
    status VARCHAR,
);

create table Measures
(
    id PRIMARY KEY,
    measure VARCHAR(100) NOT NULL,
    min_priority VARCHAR(100),
);

create table Subdivision
(
    id PRIMARY KEY,
    subdivision VARCHAR(100) NOT NULL,
);

create table Services
(
    id PRIMARY KEY,
    id_subdivision VARCHAR(100),
    service VARCHAR(100) NOT NULL,
    price VARCHAR(100) NOT NULL,
    duration timestamp(30) NOT NULL,
);