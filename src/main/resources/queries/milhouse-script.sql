drop database milhouse;

create database milhouse;

use milhouse;

CREATE TABLE bank (
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(30) NOT NULL,
	PRIMARY KEY(id)
	)
	
CREATE TABLE credit_card (
	credit_card_number INT NOT NULL AUTO_INCREMENT,
	bank_id INT NOT NULL,
	PRIMARY KEY(credit_card_number),
	FOREIGN KEY (bank_id) REFERENCES bank(id))
	
CREATE TABLE bank_account(
	account_number INT NOT NULL AUTO_INCREMENT,
	bank_id INT NOT NULL,
	balance DECIMAL,
	PRIMARY KEY(account_number),
	FOREIGN KEY (bank_id) REFERENCES bank(id))		

CREATE TABLE client ( 
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(60) NOT NULL,
	nickname varchar(30) NOT NULL,
	email varchar(60) NOT NULL,
	psw varchar(500) NOT NULL,
	salary double,
	account_number INT,
	credit_card_number INT,
	PRIMARY KEY(id),
	FOREIGN KEY (account_number) REFERENCES bank_account(account_number),
	FOREIGN KEY (credit_card_number) REFERENCES credit_card(credit_card_number))
	
CREATE TABLE invoice (
	id INT NOT NULL AUTO_INCREMENT,
	initial_date date NOT NULL,
	final_date date NOT NULL,
	payment_date date NOT NULL,
	value DECIMAL,
	credit_card_number INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (credit_card_number) REFERENCES credit_card(credit_card_number)
	)
	
CREATE TABLE transaction_type (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	PRIMARY KEY(id))
	
CREATE TABLE transactions (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(80) NOT NULL,
	transaction_date date NOT NULL,
	value DECIMAL,
	transaction_type_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(id)
	)

CREATE TABLE credit_card_transactions (
	id INT NOT NULL AUTO_INCREMENT,
	credit_card_number INT NOT NULL,
	transaction_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (transaction_id) REFERENCES transactions(id),
	FOREIGN KEY (credit_card_number) REFERENCES credit_card(credit_card_number))
	
CREATE TABLE bank_account_transactions (
	id INT NOT NULL AUTO_INCREMENT,
	account_number INT NOT NULL,
	transaction_id INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (transaction_id) REFERENCES transactions(id),
	FOREIGN KEY (account_number) REFERENCES bank_account(account_number))
	
CREATE TABLE profile (
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(30) NOT NULL,
	PRIMARY KEY(id)
)
	
CREATE TABLE client_profile (
	client_id INT NOT NULL,
	profile_id INT NOT NULL,
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (profile_id) REFERENCES profile(id)
)

INSERT INTO profile(name) values ('ROLE_ADMIN');
INSERT INTO profile(name) values ('ROLE_CLIENT');

INSERT INTO client_profile(client_id, profile_id) values (1, 1);





