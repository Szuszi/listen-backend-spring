DROP TABLE IF EXISTS "user_followings";

CREATE TABLE "user_followings" (
follower_user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
followed_user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT user_following_pkey PRIMARY KEY (follower_user_id, followed_user_id)
);

insert into user_followings(follower_user_id, followed_user_id) 
    select * from (select id as follower_user_id from users where name = 'Daniel88') t1 
    cross join (select id as followed_user_id from users where name = 'Wolfgang Amadeus Mozard') t2;
insert into user_followings(follower_user_id, followed_user_id) 
    select * from (select id as follower_user_id from users where name = 'Kanye West') t1
    cross join (select id as followed_user_id from users where name = 'Wolfgang Amadeus Mozard') t2;
insert into user_followings(follower_user_id, followed_user_id) 
    select * from (select id as follower_user_id from users where name = 'Klangkarussell') t1
    cross join (select id as followed_user_id from users where name = 'Wolfgang Amadeus Mozard') t2;
insert into user_followings(follower_user_id, followed_user_id) 
    select * from (select id as follower_user_id from users where name = 'Daniel88') t1
    cross join (select id as followed_user_id from users where name = 'Klangkarussell') t2;
insert into user_followings(follower_user_id, followed_user_id) 
    select * from (select id as follower_user_id from users where name = 'Joe_11') t1
    cross join (select id as followed_user_id from users where name = 'Kanye West') t2;