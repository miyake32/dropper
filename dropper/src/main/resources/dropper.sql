use dropper;
DROP TABLE IF EXISTS `message`;
CREATE TABLE message
(
	number SERIAL PRIMARY KEY,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	date_time DATETIME NOT NULL,
	message VARCHAR(255) NOT NULL,
	user_name VARCHAR(20),
	delete_key VARCHAR(20),
	is_active BOOLEAN DEFAULT 1
)