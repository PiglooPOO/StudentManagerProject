CREATE TABLE student(
number INTEGER PRIMARY KEY AUTOINCREMENT,
name CHAR(30) NOT NULL,
firstName CHAR(30) NOT NULL,
adress TEXT NOT NULL,
phoneNumber CHAR(10),
mail CHAR(50) NOT NULL,
birthday DATE,
gender INTEGER
);

CREATE TABLE formation(
id INTEGER PRIMARY KEY AUTOINCREMENT,
name CHAR(50) NOT NULL,
nbYear INTEGER NOT NULL,
curYear INTEGER NOT NULL,
isAvailable INTEGER
);

CREATE TABLE settings(
name CHAR(20) NOT NULL,
directorName CHAR(30) NOT NULL,
directorFirstName CHAR(30) NOT NULL
);

CREATE TABLE subject(
id INTEGER PRIMARY KEY AUTOINCREMENT,
name CHAR(30) NOT NULL,
isAvailable INTEGER
);

CREATE TABLE year_formation_student(
year INTEGER,
idFormation INTEGER,
idStudent INTEGER
);

CREATE TABLE year_formation_subject(
year INTEGER,
idFormation INTEGER,
idSubject INTEGER,
coef INTEGER
);

CREATE TABLE year_student_subject_note(
year INTEGER,
idStudent INTEGER,
idSubject INTEGER,
note INTEGER
);