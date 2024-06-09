
use master
BEGIN
Drop database if exists Spotitube
END
BEGIN
Create database Spotitube
END
BEGIN
use Spotitube
END
/*
BEGIN
Drop table playlistTrack
END
BEGIN
DROP table playlists
END
Drop table track
BEGIN
Drop table login
END
*/
--------- login --------

Create table login(
token varchar(255) NOT NULL PRIMARY KEY,
userName varchar(255) NOT NULL ,
password varchar(255) NOT NULL
)
insert into login values
('1', 'Jannis', 'Geerts'),
('2', 'test', 'test')

--select * from login where userName = 'Jannis' and password = 'Geerts'

--------- playlists --------



Create table playlists(
token varchar(255) NULL,
id int Identity(1,1) NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
FOREIGN KEY (token) REFERENCES login(token)
)
insert into playlists values
(1, 'Heavy Metal'),
(2 , 'Pop')


--select id, name, owner from playlists where token = 1

--select * from playlists

--insert into playlists values(2, 3, 'Death Metal', 0) 
--DELETE FROM playlists WHERE token = 1 and id = 1;

--------- track --------

Create table track(
id int Identity(1,1) NOT NULL PRIMARY KEY,
title varchar(255) NOT NULL,
performer  varchar(255) NOT NULL,
duration int NOT NULL,
album varchar(255)NULL,
playcount int NULL,
publicationDate varchar(255) NULL,
description varchar(255) NULL,
offlineAvailable bit NOT NULL,
)
insert into track values
('Song for someone', 'The Frames', 350, 'The cost', null, null, null, 0),
('The cost', 'The Frames', 423, null, 37,  '19-03-2006', 'Title song from the Album The Cost', 1),
('Ocean and a rock', 'Lisa Hannigan', 377, 'Sea sew', 0, null, null, 0),
('So Long, Marianne', 'Leonard Cohen', 546, 'Songs of Leonard Cohen', 0, null, null, 0),
('One', 'Metallica', 423, null, 37, '18-03-2001', 'Long version', 1)
--------- playlistTrack --------


Create table playlistTrack(
playlistId int NOT NULL,
trackId int NOT NULL,
Foreign key (playlistId) REFERENCES playlists(id),
Foreign key (trackId) REFERENCES track(id)
ON UPDATE CASCADE
)
Alter Table playlistTrack ADD PRIMARY KEY(playlistId, trackId)

insert into playlistTrack values
(1,1),
(1, 2),
(2,1),
(2,2),
(2,3)



--delete from playlistTrack where playlistId = 1

--select
select*from login
select * from playlists
Select * from track
Select * from playlistTrack


---select for het ophalen van de tracks
/*SELECT track.id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable from playlistTrack
INNER JOIN playlists ON playlistTrack.playlistId = playlists.id
INNER JOIN track ON playlistTrack.trackId = track.id
where playlistId = 1
*/
---select voor het ophalen van de availabel tracks

--SELECT * FROM track WHERE id NOT IN (SELECT trackId FROM playlistTrack WHERE playlistId = 1)

--UPDATE track SET offlineAvailable = 0 where id = 1

