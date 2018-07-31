-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22. Mai, 2018 23:52 PM
-- Server-versjon: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbeksamen2018`
--

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `bestilling`
--

CREATE TABLE `bestilling` (
  `kjoreturID` int(11) NOT NULL,
  `passasjerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `bestilling`
--

INSERT INTO `bestilling` (`kjoreturID`, `passasjerID`) VALUES
(1, 4),
(2, 5),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `bruker`
--

CREATE TABLE `bruker` (
  `brukerID` int(11) NOT NULL,
  `navn` varchar(200) CHARACTER SET latin1 NOT NULL,
  `tlf` varchar(8) CHARACTER SET latin1 NOT NULL,
  `epost` varchar(200) CHARACTER SET latin1 NOT NULL,
  `passord` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `bruker`
--

INSERT INTO `bruker` (`brukerID`, `navn`, `tlf`, `epost`, `passord`) VALUES
(1, 'Hans Hansen', '95458678', 'hans@test.com', '123456'),
(2, 'Per Askeladden', '95458679', 'per@test.com', '123456'),
(3, 'Pål Askeladden', '95478678', 'paal@test.com', '123456'),
(4, 'Espen Askeladden', '96458678', 'Espen@test.com', '123456'),
(6, 'Gro Harlem Bruntland', '45458678', 'gro@test.com', '123456'),
(7, 'Fritjof Nansen', '97458678', 'fritjof@test.com', '123456'),
(8, 'Kari Normann', '95758678', 'kari@test.com', '123456'),
(9, 'Jon Aalsgård', '95454678', 'jon@test.com', '123456'),
(10, 'Maria Mena', '48921375', 'maria@test.com', '123456'),
(11, 'Tone Damli', '95468678', 'tone@test.com', '123456');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `kjoretoy`
--

CREATE TABLE `kjoretoy` (
  `kjoretoyID` int(11) NOT NULL,
  `sjaaforID` int(11) NOT NULL,
  `merke` varchar(200) CHARACTER SET latin1 NOT NULL,
  `modell` varchar(200) CHARACTER SET latin1 NOT NULL,
  `aarsmodell` varchar(4) CHARACTER SET latin1 NOT NULL,
  `sykkelfeste` tinyint(1) NOT NULL DEFAULT '0',
  `takboks` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `kjoretoy`
--

INSERT INTO `kjoretoy` (`kjoretoyID`, `sjaaforID`, `merke`, `modell`, `aarsmodell`, `sykkelfeste`, `takboks`) VALUES
(1, 1, 'Mazda', '6', '2007', 0, 0),
(2, 2, 'Honda', 'Prelude', '1992', 0, 0),
(3, 3, 'Volkswagen', 'Passat', '2010', 1, 1),
(4, 4, 'Volkswagen', 'Golf', '2012', 1, 0);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `kjoretur`
--

CREATE TABLE `kjoretur` (
  `kjoreturID` int(11) NOT NULL,
  `dato` date NOT NULL,
  `startTid` time NOT NULL,
  `sluttTid` time NOT NULL,
  `startSted` varchar(200) CHARACTER SET latin1 NOT NULL,
  `sluttSted` varchar(200) CHARACTER SET latin1 NOT NULL,
  `kjentStopp` varchar(200) CHARACTER SET latin1 NOT NULL,
  `ledigplass` int(11) NOT NULL,
  `kjoretoyID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `kjoretur`
--

INSERT INTO `kjoretur` (`kjoreturID`, `dato`, `startTid`, `sluttTid`, `startSted`, `sluttSted`, `kjentStopp`, `ledigplass`, `kjoretoyID`) VALUES
(1, '2018-05-14', '08:00:00', '08:45:00', 'Notodden', 'Bø', 'Gvarv', 4, 1),
(2, '2018-05-21', '09:00:00', '10:15:00', 'Skien', 'Bø', 'Ulefoss', 3, 2),
(3, '2018-05-23', '15:00:00', '16:30:00', 'Bø', 'Kongsberg', 'Notodden', 2, 3),
(4, '2018-05-14', '08:00:00', '08:45:00', 'Høydalsmo', 'Bø', 'Seljord', 2, 4);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `passasjer`
--

CREATE TABLE `passasjer` (
  `passasjerID` int(11) NOT NULL,
  `brukerID` int(11) NOT NULL,
  `pickUpPoint` varchar(200) CHARACTER SET latin1 NOT NULL,
  `sisteFrist` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `passasjer`
--

INSERT INTO `passasjer` (`passasjerID`, `brukerID`, `pickUpPoint`, `sisteFrist`) VALUES
(1, 3, 'Gvarv', '12:15:00'),
(2, 9, 'Akkerhaugen', '08:00:00'),
(3, 2, 'Lunde', '09:00:00'),
(4, 6, 'Seljord', '08:15:00'),
(5, 10, 'Ulefoss', '10:00:00');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `sjaafor`
--

CREATE TABLE `sjaafor` (
  `sjaaforID` int(11) NOT NULL,
  `brukerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `sjaafor`
--

INSERT INTO `sjaafor` (`sjaaforID`, `brukerID`) VALUES
(1, 1),
(2, 4),
(3, 7),
(4, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bestilling`
--
ALTER TABLE `bestilling`
  ADD KEY `kjoreturID` (`kjoreturID`),
  ADD KEY `passasjerID` (`passasjerID`),
  ADD KEY `passasjerID_2` (`passasjerID`);

--
-- Indexes for table `bruker`
--
ALTER TABLE `bruker`
  ADD PRIMARY KEY (`brukerID`);

--
-- Indexes for table `kjoretoy`
--
ALTER TABLE `kjoretoy`
  ADD PRIMARY KEY (`kjoretoyID`),
  ADD KEY `FK_sjaafor` (`sjaaforID`);

--
-- Indexes for table `kjoretur`
--
ALTER TABLE `kjoretur`
  ADD PRIMARY KEY (`kjoreturID`),
  ADD KEY `FK_kjoretoy` (`kjoretoyID`);

--
-- Indexes for table `passasjer`
--
ALTER TABLE `passasjer`
  ADD PRIMARY KEY (`passasjerID`),
  ADD KEY `FK_bruker` (`brukerID`);

--
-- Indexes for table `sjaafor`
--
ALTER TABLE `sjaafor`
  ADD PRIMARY KEY (`sjaaforID`),
  ADD KEY `FK_brukerSjaafor` (`brukerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bruker`
--
ALTER TABLE `bruker`
  MODIFY `brukerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `kjoretoy`
--
ALTER TABLE `kjoretoy`
  MODIFY `kjoretoyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `kjoretur`
--
ALTER TABLE `kjoretur`
  MODIFY `kjoreturID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `passasjer`
--
ALTER TABLE `passasjer`
  MODIFY `passasjerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sjaafor`
--
ALTER TABLE `sjaafor`
  MODIFY `sjaaforID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Begrensninger for dumpede tabeller
--

--
-- Begrensninger for tabell `bestilling`
--
ALTER TABLE `bestilling`
  ADD CONSTRAINT `FK_kjoretur` FOREIGN KEY (`kjoreturID`) REFERENCES `kjoretur` (`kjoreturID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_passasjer` FOREIGN KEY (`passasjerID`) REFERENCES `passasjer` (`passasjerID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Begrensninger for tabell `kjoretoy`
--
ALTER TABLE `kjoretoy`
  ADD CONSTRAINT `FK_sjaafor` FOREIGN KEY (`sjaaforID`) REFERENCES `sjaafor` (`sjaaforID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Begrensninger for tabell `kjoretur`
--
ALTER TABLE `kjoretur`
  ADD CONSTRAINT `FK_kjoretoy` FOREIGN KEY (`kjoretoyID`) REFERENCES `kjoretoy` (`kjoretoyID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Begrensninger for tabell `passasjer`
--
ALTER TABLE `passasjer`
  ADD CONSTRAINT `FK_bruker` FOREIGN KEY (`brukerID`) REFERENCES `bruker` (`brukerID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Begrensninger for tabell `sjaafor`
--
ALTER TABLE `sjaafor`
  ADD CONSTRAINT `FK_brukerSjaafor` FOREIGN KEY (`brukerID`) REFERENCES `bruker` (`brukerID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
