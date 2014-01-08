
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE TABLE IF NOT EXISTS smp.`formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nbYear` int(11) NOT NULL,
  `curYear` int(11) NOT NULL,
  `isAvailable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `name`, `nbYear`, `curYear`, `isAvailable`) VALUES
(1, 'informatique', 2, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `name` varchar(20) NOT NULL,
  `directorName` varchar(30) NOT NULL,
  `directorFirstName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `settings`
--

INSERT INTO `settings` (`name`, `directorName`, `directorFirstName`) VALUES
('Pigloo', 'GOUIN', 'Pain');

-- --------------------------------------------------------

--
-- Structure de la table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `gender` tinyint(4) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `student`
--

INSERT INTO `student` (`number`, `name`, `firstName`, `adress`, `phoneNumber`, `mail`, `birthday`, `gender`) VALUES
(1, 'BOULIC', 'Quentin', '23 boulevard Aristide BRIAND', '0610101010', 'qboulic@etudiant.univ-mlv.fr', '1994-09-07', 1);

-- --------------------------------------------------------

--
-- Structure de la table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET latin1 NOT NULL,
  `isAvailable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `subject`
--

INSERT INTO `subject` (`id`, `name`, `isAvailable`) VALUES
(1, 'gestion', 1),
(2, 'automate', 1);

-- --------------------------------------------------------

--
-- Structure de la table `year_formation_student`
--

CREATE TABLE IF NOT EXISTS `year_formation_student` (
  `year` int(11) NOT NULL,
  `idFormation` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `year_formation_student`
--

INSERT INTO `year_formation_student` (`year`, `idFormation`, `idStudent`) VALUES
(2013, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `year_formation_subject`
--

CREATE TABLE IF NOT EXISTS `year_formation_subject` (
  `year` int(11) NOT NULL,
  `idFormation` int(11) NOT NULL,
  `idSubject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `year_formation_subject`
--

INSERT INTO `year_formation_subject` (`year`, `idFormation`, `idSubject`) VALUES
(2013, 1, 1),
(2013, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `year_student_subject_note`
--

CREATE TABLE IF NOT EXISTS `year_student_subject_note` (
  `year` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `idSubject` int(11) NOT NULL,
  `note` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
