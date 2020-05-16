ALTER TABLE drivers ADD COLUMN password VARCHAR(255);
ALTER TABLE drivers DROP COLUMN user_id;
DELETE FROM drivers WHERE first_name != 'Администратор';
ALTER TABLE drivers RENAME TO users;

ALTER TABLE items RENAME COLUMN assigned_driver_id TO assigned_user_id;

ALTER TABLE submissions RENAME COLUMN driver_id TO user_id;

ALTER TABLE trips RENAME COLUMN driver_id TO user_id;

ALTER TABLE addresses DROP CONSTRAINT addresses_city_id_fkey;
ALTER TABLE items DROP CONSTRAINT items_assigned_driver_id_fkey;
ALTER TABLE items DROP CONSTRAINT items_from_city_id_fkey;
ALTER TABLE items DROP CONSTRAINT items_to_city_id_fkey;
ALTER TABLE submission_comments DROP CONSTRAINT submission_comments_submission_id_fkey;
ALTER TABLE submissions DROP CONSTRAINT submissions_driver_id_fkey;
ALTER TABLE trips DROP CONSTRAINT trips_departure_address_id_fkey;
ALTER TABLE trips DROP CONSTRAINT trips_destination_address_id_fkey;
ALTER TABLE trips DROP CONSTRAINT trips_driver_id_fkey;

ALTER TABLE users ALTER COLUMN id TYPE varchar(255);
ALTER TABLE addresses ALTER COLUMN id TYPE varchar(255);
ALTER TABLE cities ALTER COLUMN id TYPE varchar(255);
ALTER TABLE contact_messages ALTER COLUMN id TYPE varchar(255);
ALTER TABLE emails ALTER COLUMN id TYPE varchar(255);
ALTER TABLE items ALTER COLUMN id TYPE varchar(255);
ALTER TABLE sms_messages ALTER COLUMN id TYPE varchar(255);
ALTER TABLE submission_comments ALTER COLUMN id TYPE varchar(255);
ALTER TABLE submissions ALTER COLUMN id TYPE varchar(255);
ALTER TABLE trips ALTER COLUMN id TYPE varchar(255);

ALTER TABLE addresses ALTER COLUMN city_id TYPE varchar(255);
ALTER TABLE items ALTER COLUMN assigned_user_id TYPE varchar(255);
ALTER TABLE items ALTER COLUMN from_city_id TYPE varchar(255);
ALTER TABLE items ALTER COLUMN to_city_id TYPE varchar(255);
ALTER TABLE items ALTER COLUMN to_city_id TYPE varchar(255);
ALTER TABLE submission_comments ALTER COLUMN submission_id TYPE varchar(255);
ALTER TABLE submissions ALTER COLUMN user_id TYPE VARCHAR(255);
ALTER TABLE trips ALTER COLUMN departure_address_id TYPE varchar(255);
ALTER TABLE trips ALTER COLUMN destination_address_id TYPE varchar(255);
ALTER TABLE trips ALTER COLUMN user_id TYPE varchar(255);

ALTER TABLE addresses ADD CONSTRAINT addresses_city_id_fkey FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE;
ALTER TABLE items ADD CONSTRAINT items_assigned_user_id_fkey FOREIGN KEY (assigned_user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE items ADD CONSTRAINT items_from_city_id_fkey FOREIGN KEY (from_city_id) REFERENCES cities(id) ON DELETE CASCADE;
ALTER TABLE items ADD CONSTRAINT items_to_city_id_fkey FOREIGN KEY (to_city_id) REFERENCES cities(id) ON DELETE CASCADE;
ALTER TABLE submission_comments ADD CONSTRAINT submission_comments_submission_id_fkey FOREIGN KEY (submission_id) REFERENCES submissions(id) ON DELETE CASCADE;
ALTER TABLE submissions ADD CONSTRAINT submissions_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE trips ADD CONSTRAINT trips_departure_address_id_fkey FOREIGN KEY (departure_address_id) REFERENCES addresses(id) ON DELETE CASCADE;
ALTER TABLE trips ADD CONSTRAINT trips_destination_address_id_fkey FOREIGN KEY (destination_address_id) REFERENCES addresses(id) ON DELETE CASCADE;
ALTER TABLE trips ADD CONSTRAINT trips_users_id_fkey FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;