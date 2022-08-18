BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS guest;
DROP TABLE IF EXISTS potluck;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS dish_type;
DROP TABLE IF EXISTS restrictions;
DROP TABLE IF EXISTS contact;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL DEFAULT 'ROLE_USER',
	restrictions varchar(500),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE potluck (
	potluck_id SERIAL,
	potluck_name VARCHAR(50) NOT NULL,
	date_time TIMESTAMP NOT NULL,
	user_id INT NOT NULL,
	description VARCHAR(200),
	CONSTRAINT PK_potluck PRIMARY KEY (potluck_id),
	CONSTRAINT Fk_host_user FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE guest (
    guest_id SERIAL,
	username VARCHAR(50) NOT NULL,
	potluck_id INT,
	CONSTRAINT PK_guest PRIMARY KEY (guest_id)
);

CREATE TABLE course (
	course_id SERIAL,
	potluck_id INT NOT NULL,
	amount_needed INT NOT NULL,
	course_name VARCHAR(50) NOT NULL,
	CONSTRAINT PK_course PRIMARY KEY (course_id),
	CONSTRAINT Fk_potluck_course FOREIGN KEY (potluck_id) REFERENCES potluck (potluck_id)

);
CREATE TABLE dish (
	dish_id SERIAL,
	dish_name VARCHAR(50) NOT NULL,
	num_of_servings INT NOT NULL,
	recipe VARCHAR(1000),
	notes VARCHAR(500),
	rating INT,
	guest_id INT ,
	course_id INT ,
	restrictions VarChar(500),
	CONSTRAINT PK_dish PRIMARY KEY (dish_id)


);

--CREATE TABLE restrictions (
--	restrictions_id SERIAL,
--	user_id INT NOT NULL,
--	dish_id INT NOT NULL,
--	guest_email VARCHAR(50) NOT NULL UNIQUE,
--	restrictions VARCHAR(50) NOT NULL,
--	CONSTRAINT PK_restrictions PRIMARY KEY (restrictions_id),
--	CONSTRAINT Fk_user_user FOREIGN KEY (user_id) REFERENCES users (user_id),
--	CONSTRAINT Fk_dish_dish FOREIGN KEY (dish_id) REFERENCES dish (dish_id),
--	CONSTRAINT Fk_guest_guest FOREIGN KEY (guest_email) REFERENCES guest (guest_email)
--
--);

CREATE TABLE contact (
	contact_id SERIAL,
	contact_name VARCHAR(100),
	contact_email VARCHAR(100),
	contact_message VARCHAR(1000)
);	

COMMIT TRANSACTION;
