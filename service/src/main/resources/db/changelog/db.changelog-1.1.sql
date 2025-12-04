--liquibase formatted sql

--changeset ashch:drop-not-null-constraint-schedule
ALTER TABLE schedule
ALTER COLUMN event_id DROP NOT NULL,
ALTER COLUMN user_id DROP NOT NULL
--rollback ALTER TABLE schedule ALTER COLUMN event_id SET NOT NULL, ALTER COLUMN user_id SET NOT NULL

--changeset ashch:alter-table-schedule-add-column-user_created
ALTER TABLE schedule
ADD COLUMN user_created INTEGER NOT NULL
REFERENCES users(id);
--rollback ALTER TABLE schedule DROP COLUMN user_created;

--changeset ashch:alter-table-extra_services-add-unique-service
ALTER TABLE extra_service
ADD CONSTRAINT uk_extra_services_service UNIQUE (service);
--rollback ALTER TABLE extra_services DROP CONSTRAINT uk_extra_services_service;

--changeset ashch:alter-table-users-update-constraint-column-room_id
ALTER TABLE users
DROP CONSTRAINT IF EXISTS users_room_id_fkey;

ALTER TABLE users
ADD CONSTRAINT users_room_id_fkey FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE SET NULL;
--rollback ALTER TABLE users DROP CONSTRAINT users_room_id_fkey;
--rollback ALTER TABLE users ADD CONSTRAINT users_room_id_fkey FOREIGN KEY (room_id) REFERENCES room (id);
