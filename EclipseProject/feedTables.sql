INSERT INTO 
`settings` (`name`, `directorName`, `directorFirstName`) 
VALUES
('Pigloo', 'GOUIN', 'Pain');

INSERT INTO 
`student` (`name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) 
VALUES 
("D","s","3 rue des etangs","0129436789","sd@yopmail.com","1993-10-24",1),
("U","f","9 rue des pieuvres","0123456789","fu@yopmail.com","1993-2-17",2),
("H","l","3 rue des tulipes","0129436789","lh@yopmail.com","1993-4-2",1),
("B","m","19 rue des poneys","0123456789","mb@yopmail.com","1994-1-12",1),
("E","h","1287 avenue de la republique","0129436789","he@yopmail.com","1993-7-9",2),
("W","d","9 rue des mangoustes","0123456789","dw@yopmail.com","1993-2-15",1);

INSERT INTO 
`formation` (`name`, `nbYear`, `curYear`, `isAvailable`) 
VALUES
('informatique', 2, 1, 1),
('informatique', 2, 2, 1),
('eco-gestion', 2, 1, 1),
('eco-gestion', 2, 2, 1),
('management', 2, 1, 1),
('management', 2, 2, 1);

INSERT INTO `subject` (`name`, `isAvailable`) VALUES
('gestion', 1),
('communication', 1),
('langage C', 1),
('langage PHP', 1),
('programmation objet', 1),
('genie logiciel', 1),
('dessin', 1),
('mathematiques', 1),
('histoire', 1),
('francais', 1),
('babyfoot', 0),
('sport', 1),
('exposes', 1);
