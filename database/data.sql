BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, restrictions) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','Vegan');
INSERT INTO users (username,password_hash,role, restrictions) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN','Vegan');

INSERT INTO potluck (potluck_name,date_time,user_id, description) VALUES ('Potluck','2022-08-12T12:54:00',1,'This is a potluck');
INSERT INTO course (potluck_id,amount_needed,course_name) VALUES ('1','4','Main Course');
INSERT INTO course (potluck_id,amount_needed,course_name) VALUES ('1','4','Appetizers');
INSERT INTO course (potluck_id,amount_needed,course_name) VALUES ('1','4','Desserts');
INSERT INTO course (potluck_id,amount_needed,course_name) VALUES ('1','4','Beverages');
INSERT INTO guest (potluck_id,username) VALUES ('1','user');
INSERT INTO dish (dish_name,num_of_servings,recipe, rating, guest_id, notes, course_id, restrictions) VALUES ('Pizza',4,'Put sauce and cheese on raw bread', 5, 1, 'Za', 1, 'Vegan');
INSERT INTO dish (dish_name,num_of_servings,recipe, rating, guest_id, notes, course_id, restrictions) VALUES ('Brownies',4,'Moar Chocolate', 5, 1, 'Za', 1, 'Tree Nut-Free, Shellfish-Free');


COMMIT TRANSACTION;
