CREATE TABLE USER
(
    id           INT AUTO_INCREMENT PRIMARY KEY NOT NULL default 1,
    account_id   VARCHAR(20),
    name         VARCHAR(50),
    token        CHAR(36),
    gmt_create   BIGINT,
    gmt_modified BIGINT,
    bio VARCHAR(256),
    avatar_url VARCHAR (100),
);
    create unique index user_account_id_uindex
	on user (account_id);