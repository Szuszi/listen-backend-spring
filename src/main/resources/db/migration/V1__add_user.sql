DROP TABLE IF EXISTS "users";

CREATE TABLE "users" (
id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
name VARCHAR(255) NOT NULL,
description VARCHAR(255),
avatar_url VARCHAR(255),
created_on DATE,
last_modified_on DATE
);

insert into users(name, description, avatar_url) values ('Wolfgand Amadeus Mozard', ' Prolific and influential composer of the Classical period', 'https://ui-avatars.com/api/?name=Mozart');
insert into users(name, description, avatar_url) values ('Kanye West', 'ye', 'https://ui-avatars.com/api/?name=Kanye');
insert into users(name, description, avatar_url) values ('Klangkarussell', '', 'https://ui-avatars.com/api/?name=Klangkarussell');
insert into users(name, description, avatar_url) values ('Joe_11', 'I just listen to music', 'https://ui-avatars.com/api/?name=Joe_11');
insert into users(name, description, avatar_url) values ('Daniel88', '', 'https://ui-avatars.com/api/?name=Daniel88');