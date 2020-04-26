create table question
(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator VARCHAR(20),
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag VARCHAR(256)
);