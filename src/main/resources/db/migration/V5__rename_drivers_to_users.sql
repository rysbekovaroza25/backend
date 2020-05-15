ALTER TABLE drivers ADD COLUMN password VARCHAR(255);
ALTER TABLE drivers DROP COLUMN user_id;
DELETE FROM drivers WHERE first_name != 'Администратор';
ALTER TABLE drivers RENAME TO users;

ALTER TABLE items RENAME COLUMN assigned_driver_id TO assigned_user_id;
ALTER TABLE items ALTER COLUMN assigned_user_id TYPE VARCHAR(255);

ALTER TABLE submissions RENAME COLUMN driver_id TO user_id;
ALTER TABLE submissions ALTER COLUMN user_id TYPE VARCHAR(255);

ALTER TABLE trips RENAME COLUMN driver_id TO user_id;
ALTER TABLE trips ALTER COLUMN user_id TYPE VARCHAR(255);