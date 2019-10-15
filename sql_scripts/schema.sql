CREATE SEQUENCE main_seq START WITH 1;

CREATE TABLE usr(
	id INT PRIMARY KEY,
	username VARCHAR(100),
	password VARCHAR(100),
	avatar VARCHAR(255),
	email VARCHAR(255),
	phone VARCHAR(10),
	town VARCHAR(255),
	gl_id INT
	);
	
CREATE TABLE givelist(
	id INT PRIMARY KEY,
	usr_id INT,
	CONSTRAINT fk_usr_id FOREIGN KEY (usr_id) REFERENCES usr(id)
	);
	
CREATE TABLE item(
	id INT PRIMARY KEY,
	title VARCHAR(100),
	photo VARCHAR(255),
	description VARCHAR(255),
	categories BYTEA,
	gl_id INT,
	CONSTRAINT fk_gl_id FOREIGN KEY (gl_id) REFERENCES givelist(id)
	);
	
CREATE TABLE crush(
	usr_id INT,
	item_id INT,
	CONSTRAINT fk_usr_id FOREIGN KEY (usr_id) REFERENCES usr(id),
	CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES item(id)
	);
	
ALTER TABLE usr ADD CONSTRAINT fk_gl_id FOREIGN KEY (gl_id) REFERENCES givelist(id);