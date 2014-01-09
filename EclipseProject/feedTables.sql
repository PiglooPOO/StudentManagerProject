INSERT INTO 
`settings` (`name`, `directorName`, `directorFirstName`) 
VALUES
('Pigloo', 'GOUIN', 'Pain');

INSERT INTO 
`student` (`number`, `name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) 
VALUES 
(1,"GERBER","Xavier","3 rue des etangs","0129436789","xg@yopmail.com","1993-10-24",1),
(2,"BEAUNE","Louis","9 rue des pieuvres","0123456789","lb@yopmail.com","1993-2-17",1),
(3,"BOULIC","Quentin","3 rue des tulipes","0129436789","qb@yopmail.com","1993-4-2",1),
(4,"MIGNON","Frederic","19 rue des poneys","0123456789","fm@yopmail.com","1994-1-12",1);

INSERT INTO 
`formation` (`id`, `name`, `nbYear`, `curYear`, `isAvailable`) 
VALUES
(1,'informatique', 2, 1, 1),
(2,'informatique', 2, 2, 1),
(3,'eco-gestion', 2, 1, 1),
(4,'eco-gestion', 2, 2, 1),
(5,'management', 2, 1, 1),
(6,'management', 2, 2, 1);

INSERT INTO `subject` (`id`, `name`, `isAvailable`) VALUES
(1,'gestion', 1),
(2,'communication', 1),
(3,'langage C', 1),
(4,'langage PHP', 1),
(5,'programmation objet', 1),
(6,'genie logiciel', 1),
(7,'dessin', 1),
(8,'mathematiques', 1),
(9,'histoire', 1),
(10,'francais', 1),
(11,'babyfoot', 0),
(12,'sport', 1),
(13,'exposes', 1);

INSERT INTO `year_formation_student` (`year`, `idFormation`, `idStudent`) VALUES
(2013,2, 1),
(2013,3, 2),
(2013,1, 3),
(2013,2, 1);

INSERT INTO `year_formation_subject` (`year`, `idFormation`, `idSubject`, `coef`) VALUES
(2013,2, 1,2),
(2013,2, 3,5),
(2013,2, 4,7),
(2013,1, 1,1),
(2013,1, 2,1),
(2013,1, 5,7);

INSERT INTO `year_student_subject_note` (`year`, `idStudent`, `idSubject`, `note`) VALUES
(2013,1, 1,15),
(2013,1, 3,12),
(2013,1, 4,16),
(2013,2, 1,1),
(2013,2, 2,1),
(2013,2, 5,7);