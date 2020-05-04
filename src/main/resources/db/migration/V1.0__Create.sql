CREATE TABLE USER
(
    account_id   VARCHAR(20) PRIMARY KEY NOT NULL,
    name         VARCHAR(50),
    token        CHAR(36),
    gmt_create   BIGINT,
    gmt_modified BIGINT,
    bio VARCHAR(256),
    avatar_url VARCHAR (100)
);