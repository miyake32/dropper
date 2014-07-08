DROP DATABASE IF EXISTS dropper;
CREATE DATABASE dropper; 
USE dropper;
CREATE TABLE message
(
	number BIGINT PRIMARY KEY,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	date_time DATETIME NOT NULL,
	message VARCHAR(255) CHARACTER SET utf8 NOT NULL,
	user_name VARCHAR(20) CHARACTER SET utf8,
	delete_key VARCHAR(20) CHARACTER SET utf8,
	is_active BOOLEAN DEFAULT 1
);

INSERT INTO message VALUES (0, 0.0, 0.0, "1990-1-1-0-0-0",	"",	"",	"",	0);