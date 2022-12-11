DROP TABLE IF EXISTS "track_favorites";

CREATE TABLE "track_favorites" (
favorited_user_track_id BIGINT REFERENCES user_tracks (id) ON UPDATE CASCADE ON DELETE CASCADE,
favorites_user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT track_favorites_pkey PRIMARY KEY (favorited_user_track_id, favorites_user_id)
);

insert into track_favorites(favorited_user_track_id, favorites_user_id) 
    select * from (select id as favorited_user_track_id from user_tracks where name = 'Die Hochzeit des Figaro') t1 
    cross join (select id as favorites_user_id from users where name = 'Daniel88') t2;
insert into track_favorites(favorited_user_track_id, favorites_user_id) 
    select * from (select id as favorited_user_track_id from user_tracks where name = 'Die Hochzeit des Figaro') t1 
    cross join (select id as favorites_user_id from users where name = 'Klangkarussell') t2;
insert into track_favorites(favorited_user_track_id, favorites_user_id) 
    select * from (select id as favorited_user_track_id from user_tracks where name = 'Die Hochzeit des Figaro') t1 
    cross join (select id as favorites_user_id from users where name = 'Kanye West') t2;
insert into track_favorites(favorited_user_track_id, favorites_user_id) 
    select * from (select id as favorited_user_track_id from user_tracks where name = 'Sonnentanz - Sun Don''t Shine') t1 
    cross join (select id as favorites_user_id from users where name = 'Daniel88') t2;
insert into track_favorites(favorited_user_track_id, favorites_user_id) 
    select * from (select id as favorited_user_track_id from user_tracks where name = 'POWER') t1 
    cross join (select id as favorites_user_id from users where name = 'Kanye West') t2;
