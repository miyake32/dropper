
use dropper;


CREATE TABLE `message` (
  `number` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `date_time` datetime NOT NULL,
  `message` varchar(255) CHARACTER SET latin1 NOT NULL,
  `user_name` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `delete_key` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`number`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO message (latitude, longitude, date_time, message, user_name, delete_key) VALUES (35.443708, 139.638026, "1990-03-02-15-30-24", "I am test\nI am at Yokohama\nDelete key is test", "test", "test");
INSERT INTO message (latitude, longitude, date_time, message, user_name) VALUES (34.693738, 135.502165, "1995-04-01-05-34-20", "I am spam\nI am at Osaka\nDelete key is not set", "spam");
INSERT INTO message (latitude, longitude, date_time, message) VALUES (33.590355, 130.401716, "2000-03-02-15-30-24", "I am nobody\nI am at Fukuoka\ndelete key is not set");
INSERT INTO message (latitude, longitude, date_time, message, delete_key) VALUES (34.693738, 135.502165, "1995-04-01-05-34-20", "I am spam\nI am at Osaka\nDelete key is ham", "ham");

select * from message;

