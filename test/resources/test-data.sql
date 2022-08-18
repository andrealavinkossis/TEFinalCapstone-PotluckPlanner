BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS guest;
DROP TABLE IF EXISTS potluck;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS dish_type;
DROP TABLE IF EXISTS restrictions;
DROP TABLE IF EXISTS contact;

-- DROP TABLE IF EXISTS users, guest, potluck, dish, dish_type, restrictions, contact CASCADE;

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
	CONSTRAINT PK_guest PRIMARY KEY (guest_id),
	CONSTRAINT Fk_potluck_guest FOREIGN KEY (potluck_id) REFERENCES potluck (potluck_id)
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
	restrictions VarChar(50),
	CONSTRAINT PK_dish PRIMARY KEY (dish_id)
);

INSERT INTO users (username,password_hash,role, restrictions) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','Vegan');
INSERT INTO users (username,password_hash,role, restrictions) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN','Vegan');

INSERT INTO potluck (potluck_name,date_time,user_id, description)
VALUES  ('Potluck','2022-08-12T12:54:00',1,'This is a potluck'),
        ('Bob Potluck','2022-08-12T12:54:00',2,'This is another potluck'),
        ('Cassie Potluck','2022-08-12T12:54:00',2,'This is another potluck');

INSERT INTO course (potluck_id,amount_needed,course_name)
VALUES  ('1','4','Main Course'),
        ('1','3','Dessert'),
        ('1','2','Beverages'),
        ('2','2','Beverages'),
        ('2','3','Dessert'),
        ('2','4','Main Course'),
        ('3','4','Main Course');

INSERT INTO guest (potluck_id,username)
VALUES ('1','user');

INSERT INTO dish (dish_name,num_of_servings,recipe, rating, guest_id, notes, course_id, restrictions)
VALUES  ('Pizza',4,'Put sauce and cheese on raw bread', 5, 1, 'Za', 1, 'Vegan'),
        ('Brownies',4,'Moar Chocolate', 5, 1, 'Za', 1, 'Tree Nut-Free, Shellfish-Free');


COMMIT TRANSACTION;

