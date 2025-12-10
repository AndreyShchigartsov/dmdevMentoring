--liquibase formatted sql

--changeset ashch:create-index-only_one_admin_idx
CREATE UNIQUE INDEX only_one_admin_idx
ON users (role)
WHERE role = 'ADMIN';
--rollback DROP INDEX only_one_admin_idx