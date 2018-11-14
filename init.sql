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
CREATE TABLE "USERNAME"."show"(
	showId BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	filmId BIGINT NOT NULL REFERENCES "USERNAME"."film"(filmId),
	roomName VARCHAR(30) NOT NULL REFERENCES "USERNAME"."room"(roomName),
	startAtDatetime DATE NOT NULL
);
CREATE TABLE "USERNAME"."room"(
	roomName VARCHAR(30) NOT NULL PRIMARY KEY,
	rowNr INT NOT NULL;
	columnNr INT NOT NULL
);
CREATE TABLE "USERNAME"."seat"(
	seatId BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	showId BIGINT NOT NULL REFERENCES "USERNAME"."show"(showId),
	roomName VARCHAR(30) NOT NULL REFERENCES "USERNAME"."room"(roomName),
	rowIdx INT NOT NULL,
	columnIdx INT NOT NULL
);

INSERT INTO "USERNAME"."film" (title, country, syncronized, director, storyline, runtime, licenseToPlay, ageLimit) VALUES
	('The Shawshank Redemption',
	'US',
	'Y',
	'Frank Darabont',
	'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
	'142',
	'10',
	'2'),
	('The Godfather',
	'US',
	'N',
	'Francis Ford Coppola',
	'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
	'175',
	'10',
	'2'),
	('The Dark Knight',
	'US',
	'N',
	'Christopher Nolan',
	'When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
	'152',
	'10',
	'1'),
	('12 Angry Men',
	'US',
	'N',
	'Sidney Lumet',
	'A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.',
	'96',
	'10',
	'1'),
	('Pulp Fiction',
	'US',
	'N',
	'Quentin Tarantino',
	'The lives of two mob hitmen, a boxer, a gangster''s wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
	'154',
	'10',
	'3')
	('Seven Samurai',
	'JA',
	'Y',
	'Akira Kurosawa',
	'A poor village under attack by bandits recruits seven unemployed samurai to help them defend themselves.',
	'207',
	'10',
	'1');

INSERT INTO "USERNAME"."room" (roomName, rowNr, columnNr) VALUES
	('','',''),
	('','',''),
	('','',''),
	('','','');