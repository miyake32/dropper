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

INSERT INTO message VALUES (0, 0.0, 0.0, "1990-1-1-0-0-0",	"",	"",	"",	0);
INSERT INTO remessage VALUES (0, 0.0, 0.0, "1990-1-1-0-0-0", "", "", "", 0, 0);

INSERT INTO message (number, latitude, longitude, date_time, message, user_name, delete_key) VALUES (1, 35.443708, 139.638026, "1990-03-02-15-30-24", "I am test\nI am at Yokohama\nDelete key is test", "test", "test");
INSERT INTO message (number, latitude, longitude, date_time, message, user_name) VALUES (2, 34.693738, 135.502165, "1995-04-01-05-34-20", "I am spam\nI am at Osaka\nDelete key is not set", "spam");
INSERT INTO message (number, latitude, longitude, date_time, message) VALUES (3, 33.590355, 130.401716, "2000-03-02-15-30-24", "I am anonymous\nI am at Fukuoka\ndelete key is not set");
INSERT INTO message (number, latitude, longitude, date_time, message, delete_key) VALUES (4, 34.693738, 135.502165, "1995-04-01-05-34-20", "I am anonymous\nI am at Osaka\nDelete key is ham", "ham");

INSERT INTO remessage (number, latitude, longitude, date_time, message, user_name, delete_key, reply_to) VALUES (1, 35.443708, 139.638026, "1990-03-02-15-30-24", "I am test\nI am at Yokohama\nDelete key is test", "test", "test", 1);
INSERT INTO remessage (number, latitude, longitude, date_time, message, user_name, reply_to) VALUES (2, 34.693738, 135.502165, "1995-04-01-05-34-20", "I am spam\nI am at Osaka\nDelete key is not set", "spam", 2);
INSERT INTO remessage (number, latitude, longitude, date_time, message, reply_to) VALUES (3, 33.590355, 130.401716, "2000-03-02-15-30-24", "I am anonymous\nI am at Fukuoka\ndelete key is not set", 3);
INSERT INTO remessage (number, latitude, longitude, date_time, message, delete_key, reply_to) VALUES (4, 34.693738, 135.502165, "1995-04-01-05-34-20", "I am anonymous\nI am at Osaka\nDelete key is ham", "ham", 2);

SELECT * FROM message LEFT JOIN remessage ON message.number=remessage.reply_to;
