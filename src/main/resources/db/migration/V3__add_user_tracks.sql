DROP TABLE IF EXISTS "user_tracks";

CREATE TABLE "user_tracks" (
id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
owner_user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
name VARCHAR(255) NOT NULL,
picture_url VARCHAR(255),
audio_url VARCHAR(255),
created_on DATE,
last_modified_on DATE
);

insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'Requiem, K. 626: Lacrimosa', 'https://picsum.photos/200?random=1', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3' from users where name = 'Wolfgang Amadeus Mozard';
insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'Die Hochzeit des Figaro', 'https://picsum.photos/200?random=2', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3' from users where name = 'Wolfgang Amadeus Mozard';
insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'POWER', 'https://picsum.photos/200?random=3', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3' from users where name = 'Kanye West';
insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'Flashing Lights', 'https://picsum.photos/200?random=4', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3' from users where name = 'Kanye West';
insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'Sonnentanz - Sun Don''t Shine', 'https://picsum.photos/200?random=5', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3' from users where name = 'Klangkarussell';
insert into user_tracks(owner_user_id, name, picture_url, audio_url) select id, 'Plastic', 'https://picsum.photos/200?random=6', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3' from users where name = 'Klangkarussell';