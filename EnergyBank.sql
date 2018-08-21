CREATE TABLE user_account(
	user_id SERIAL UNIQUE PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	firstname VARCHAR(20) NOT NULL,
	lastname VARCHAR(20) NOT NULL,
	age INTEGER NOT NULL,
	balance NUMERIC NOT NULL
)

CREATE TABLE admin_account(
	admin_id SERIAL UNIQUE PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	firstname VARCHAR(20) NOT NULL,
	lastname VARCHAR(20) NOT NULL
)

CREATE TABLE transaction_history(
	transaction_id SERIAL UNIQUE PRIMARY KEY,
	user_action VARCHAR(50) NOT NULL,
	action_date VARCHAR(50) NOT NULL,
	user_id INTEGER REFERENCES user_account(user_id)
)