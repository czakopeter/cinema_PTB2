CREATE TABLE "USERNAME"."film"(
	filmId BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	title VARCHAR(50) NOT NULL,
	country VARCHAR(2) NOT NULL,
	syncronized VARCHAR(1) NOT NULL,
	director VARCHAR(50) NOT NULL,
	storyline VARCHAR(300) NOT NULL, 
	runtime INT NOT NULL,
	licenseToPlay INT NOT NULL,
	ageLimit VARCHAR(1) NOT NULL
);
CREATE TABLE "USERNAME"."room"(
	roomName VARCHAR(30) NOT NULL PRIMARY KEY,
	rowNr INT NOT NULL,
	columnNr INT NOT NULL
);
CREATE TABLE "USERNAME"."show"(
	showId BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	filmId BIGINT NOT NULL REFERENCES "USERNAME"."film"(filmId),
	roomName VARCHAR(30) NOT NULL REFERENCES "USERNAME"."room"(roomName),
	startDate DATE NOT NULL,
	startTime TIME NOT NULL
);
CREATE TABLE "USERNAME"."seats"(
	seatsId BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	showId BIGINT NOT NULL REFERENCES "USERNAME"."show"(showId),
	roomName VARCHAR(30) NOT NULL REFERENCES "USERNAME"."room"(roomName),
	seatsStatus VARCHAR(200) NOT NULL
);

INSERT INTO "USERNAME"."film" (title, country, syncronized, director, storyline, runtime, licenseToPlay, ageLimit) VALUES
	('Twelve Monkeys',
	'US',
	'N',
	'Terry Gilliam',
	'In a future world devastated by disease, a convict is sent back in time to gather information about the man-made virus that wiped out most of the human population on the planet.',
	129,
	10,
	'2'),
	('Intouchables',
	'FR',
	'Y',
	'Olivier Nakache',
	'After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.',
	112,
	10,
	'1'),
	('The Dark Knight',
	'US',
	'N',
	'Christopher Nolan',
	'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
	152,
	10,
	'2'),
	('Memento',
	'US',
	'N',
	'Christopher Nolan',
	'A man with short-term memory loss attempts to track down his wife''s murderer.',
	113,
	10,
	'2'),
	('Your name',
	'JP',
	'N',
	'Makoto Shinkai',
	'Two strangers find themselves linked in a bizarre way. When a connection forms, will distance be the only thing to keep them apart?',
	106,
	10,
	'1'),
	('Oldboy',
	'KR',
	'N',
	'Chan-wook Park',
	'After being kidnapped and imprisoned for fifteen years, Oh Dae-Su is released, only to find that he must find his captor in five days.',
	120,
	10,
	'3'),
	('Adam''s apples',
	'DK',
	'N',
	'Anders Thomas Jensen',
	'A neo-nazi sentenced to community service at a church clashes with the blindly devotional priest.',
	94,
	10,
	'3'),
	('Wild Tales',
	'AR',
	'Y',
	'Damian Szifron',
	'Six short stories that explore the extremities of human behavior involving people in distress.',
	122,
	10,
	'2'),
	('Young Einstein',
	'AU',
	'N',
	'Yahoo Serious',
	'Albert Einstein is the son of a Tasmanian apple farmer, who discovers the secret of splitting the beer atom to put the bubbles back into beer.',
	91,
	10,
	'1'),
	('Tangerines',
	'EE',
	'N',
	'Zaza Urushadze',
	'War in Georgia, Apkhazeti region in 1990. An Estonian man Ivo has stayed behind to harvest his crops of tangerines. In a bloody conflict at his door, a wounded man is left behind, and Ivo is forced to take him in.',
	87,
	10,
	'2');

INSERT INTO "USERNAME"."room" (roomName, rowNr, columnNr) VALUES
	('Batcave',3,4),
	('Stargate',4,3),
	('Scarface',4,4),
	('Nautilus',1,2),
	('Terminator',3,3),
	('Room6',8,10),
	('Room7',8,10),
	('Room8',8,10),
	('Room9',8,10),
	('Room10',8,10);

INSERT INTO "USERNAME"."show" (filmId, roomName, startDate, startTime) VALUES
	(3,
	'Batcave',
	'2018-12-12',
	'17:00'
	),
	(1,
	'Batcave',
	'2018-12-12',
	'18:30'
	),
	(3,
	'Stargate',
	'2018-12-12',
	'21:00'
	),
	(4,
	'Stargate',
	'2018-12-12',
	'18:00'
	),
	(5,
	'Terminator',
	'2018-12-12',
	'14:30'
	),
	(10,
	'Terminator',
	'2018-12-12',
	'17:30'
	),
	(7,
	'Terminator',
	'2018-12-12',
	'19:30'
	),
	(6,
	'Terminator',
	'2018-12-12',
	'21:45'
	),
	(5,
	'Terminator',
	'2018-12-13',
	'16:00'
	),
	(4,
	'Terminator',
	'2018-12-13',
	'18:20'
	);

INSERT INTO "USERNAME"."seats" (showId, roomName, seatsStatus) VALUES
	(1,
	'Batcave',
	'AAAAAAAAAAAA'
	),
	(2,
	'Batcave',
	'AAAAAAAAAAAA'
	),
	(3,
	'Stargate',
	'AAAAAAAAAAAA'
	),
	(4,
	'Stargate',
	'AAAAAAAAAAAA'
	),
	(5,
	'Terminator',
	'AAAAAAAAA'
	),
	(6,
	'Terminator',
	'AAAAAAAAA'
	),
	(7,
	'Terminator',
	'AAAAAAAAA'
	),
	(8,
	'Terminator',
	'AAAAAAAAA'
	),
	(9,
	'Terminator',
	'AAAAAAAAA'
	),
	(10,
	'Terminator',
	'AAAAAAAAA'
	);