ALTER TABLE users DROP COLUMN passwordHash;

ALTER TABLE users ADD COLUMN first_name TEXT DEFAULT NULL;
ALTER TABLE users ADD COLUMN last_name TEXT DEFAULT NULL;