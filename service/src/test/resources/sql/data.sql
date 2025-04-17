INSERT INTO structure_division (id, parent_id, type_structure, name)
VALUES (1, null, 'ORGANIZATIONAL', 'Vita'),
       (2, null, 'ORGANIZATIONAL', 'Avrora'),
       (3, 1, 'PROVINCE', 'Sun province'),
       (4, 1, 'PROVINCE', 'Northern province'),
       (5, 1, 'PROVINCE', 'Central province'),
       (6, 1, 'PROVINCE', 'Southern province'),
       (7, 2, 'PROVINCE', 'Blue province'),
       (8, 2, 'PROVINCE', 'Green province'),
       (9, 2, 'PROVINCE', 'Orange province'),
       (10, 3, 'GROUP', '11'),
       (11, 3, 'GROUP', '12'),
       (12, 3, 'GROUP', '13'),
       (13, 4, 'GROUP', '21'),
       (14, 4, 'GROUP', '22'),
       (15, 5, 'GROUP', '31'),
       (16, 7, 'GROUP', '51'),
       (17, 7, 'GROUP', '52'),
       (18, 7, 'GROUP', '53'),
       (19, 8, 'GROUP', '61'),
       (20, 8, 'GROUP', '62');
SELECT SETVAL('structure_division_id_seq', (SELECT MAX(id) FROM structure_division));

INSERT INTO room(id, corps, room_number, seats_value, province_id)
VALUES (1, '3ий корпус(аврора)', 101, 4, 8),
       (2, '3ий корпус(аврора)', 102, 4, 8),
       (3, '3ий корпус(аврора)', 103, 4, 8),
       (4, '3ий корпус(аврора)', 104, 4, 8),
       (5, '4ий корпус(аврора)', 101, 4, 9),
       (6, '4ий корпус(аврора)', 102, 4, 9),
       (7, '5ий корпус(Вита)', 101, 2, 4),
       (8, '5ий корпус(Вита)', 102, 2, 4),
       (9, '5ий корпус(Вита)', 103, 2, 4),
       (10, '5ий корпус(Вита)', 131, 4, 5),
       (11, '5ий корпус(Вита)', 132, 4, 5),
       (12, '5ий корпус(Вита)', 133, 4, 5),
       (13, '5ий корпус(Вита)', 161, 4, 6),
       (14, '5ий корпус(Вита)', 162, 4, 6);
SELECT SETVAL('room_id_seq', (SELECT MAX(id) FROM room));

INSERT INTO users(id, username, lastname, password, date_registration, email, active, role, salary, job_position, room_id, structure_division_id)
VALUES (1, 'Andrey', 'Shchigartsov', '12345', '10-05-2025', 'a.shch@yandex.ru', true, USER, 30000, 'SENIOR_COUNSELOR', 1, 1),
       (2, 'Slava', 'Shchigartsov', '123456', '10-05-2025', 'slava.shchigartsov@yandex.ru', true, USER, 300000, 'CAMP_DIRECTOR', 2, 1),
       (3, 'Valery', 'Orlov', '123456', '10-05-2025', 'valera.orlov@yandex.ru', true, USER, 300000, 'SENIOR_COUNSELOR', 2, 1);
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO event(id, name, category)
VALUES (1, 'Баскетбол', 'SPORT'),
       (2, 'Волейбол', 'SPORT'),
       (3, 'Футбол', 'SPORT'),
       (4, 'Астрономия', 'SCIENCE'),
       (5, 'Програмирование', 'SCIENCE'),
       (6, 'Математика', 'SCIENCE'),
       (7, 'Физика', 'SCIENCE'),
       (8, 'Шахматы', 'CHESS'),
       (9, 'Шахматы фишера', 'CHESS');
SELECT SETVAL('event_id_seq', (SELECT MAX(id) FROM event));

INSERT INTO schedule(id, user_id, event_id, structure_division_id, date_time, status, action)
VALUES (1, 1, 2, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (2, 1, 3, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (3, 2, 2, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (4, 2, 3, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (5, 2, 4, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (6, 3, 3, null, '2024-05-20 14:30:00', 'ACTIVE', null),
       (7, 3, 2, null, '2024-05-20 14:30:00', 'ACTIVE', null);
SELECT SETVAL('schedule_id_seq', (SELECT MAX(id) FROM schedule));

INSERT INTO extra_service(id, structure_division_id, service, price, duration)
VALUES (1, 1, 'Шоколадная фабрика', 2500, INTERVAL '200 minutes'),
       (2, 1, 'Сап-серфинг', 800, INTERVAL '20 minutes'),
       (3, 1, 'Квадрациклы', 800, INTERVAL '20 minutes'),
       (4, 1, 'Дельфинарий', 800, INTERVAL '20 minutes'),
       (5, 1, 'Самокат', 800, INTERVAL '20 minutes'),
       (6, 1, 'Плавание', 500, INTERVAL '30 minutes');
SELECT SETVAL('extra_service_id_seq', (SELECT MAX(id) FROM extra_service));

INSERT INTO image(id, path, user_id)
VALUES (1, 'image/andrey/1.png', 1),
       (2, 'image/andrey/2.png', 1),
       (3, 'image/andrey/3.png', 1),
       (4, 'image/andrey/4.png', 1),
       (5, 'image/slava/1.png', 2),
       (6, 'image/slava/2.png', 2),
       (7, 'image/slava/3.png', 2),
       (8, 'image/slava/4.png', 2),
       (9, 'image/slava/5.png', 2);
SELECT SETVAL('image_id_seq', (SELECT MAX(id) FROM image));