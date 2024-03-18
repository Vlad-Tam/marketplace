CREATE TABLE IF NOT EXISTS city (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	region VARCHAR(100) NOT NULL,
	name VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS address (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	street VARCHAR(100) NOT NULL,
	house_number INTEGER NOT NULL,
	flat_number INTEGER,
	id_city BIGINT NOT NULL,	
	
	CONSTRAINT fk_city FOREIGN KEY (id_city) REFERENCES city (id)
);


CREATE TABLE IF NOT EXISTS user_account (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(50) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	registration_date TIMESTAMP DEFAULT now(),
	id_address BIGINT NOT NULL,
	
	CONSTRAINT fk_address FOREIGN KEY (id_address) REFERENCES address (id)
);


CREATE TABLE IF NOT EXISTS review (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	rating INTEGER NOT NULL,
	comment VARCHAR(500) NOT NULL,
	id_receiver BIGINT NOT NULL,
	id_sender BIGINT NOT NULL,
	
	CONSTRAINT fk_receiver FOREIGN KEY (id_receiver) REFERENCES user_account (id),
	CONSTRAINT fk_sender FOREIGN KEY (id_sender) REFERENCES user_account (id)
);


CREATE TABLE IF NOT EXISTS category (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(500)
);


CREATE TABLE IF NOT EXISTS advertisement (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(500) NOT NULL,
	price NUMERIC NOT NULL,
	status VARCHAR(30) NOT NULL,
	id_vendor BIGINT NOT NULL,
	id_cathegory BIGINT NOT NULL,
	
	CONSTRAINT fk_vendor FOREIGN KEY (id_vendor) REFERENCES user_account (id),
	CONSTRAINT fk_cathegory FOREIGN KEY (id_cathegory) REFERENCES category (id)
);


CREATE TABLE IF NOT EXISTS photo (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	data BYTEA NOT NULL,
	id_advertisement BIGINT NOT NULL,
	
	CONSTRAINT fk_advertisement FOREIGN KEY (id_advertisement) REFERENCES advertisement (id)
);


CREATE TABLE IF NOT EXISTS wish_list (
	id_user BIGINT NOT NULL,
	id_advertisement BIGINT NOT NULL,
	PRIMARY KEY(id_user, id_advertisement),
	
	CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES user_account (id),
	CONSTRAINT fk_advertisement FOREIGN KEY (id_advertisement) REFERENCES advertisement (id)
);