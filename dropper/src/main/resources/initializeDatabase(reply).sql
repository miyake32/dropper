DROP DATABASE IF EXISTS dropper;
CREATE DATABASE dropper; 
USE dropper;
CREATE TABLE simplemessage
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

CREATE TABLE remessage
(
	number BIGINT PRIMARY KEY,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	date_time DATETIME NOT NULL,
	message VARCHAR(255) CHARACTER SET utf8 NOT NULL,
	user_name VARCHAR(20) CHARACTER SET utf8,
	delete_key VARCHAR(20) CHARACTER SET utf8,
	is_active BOOLEAN DEFAULT 1,
	reply_to BIGINT
);



INSERT INTO message VALUES (0, 0.0, 0.0, "1990-1-1-0-0-0",	"",	"",	"",	0, 0);
INSERT INTO message VALUES (1, 30, 130, "2014-7-8", "test", "user", "delete", 1, 0);