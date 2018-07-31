-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 24. Mai, 2018 14:51 PM
-- Server-versjon: 10.0.32-MariaDB
-- PHP Version: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Tabellstruktur for tabell `bruker`
--

CREATE TABLE `bruker` (
  `brukerID` int(11) NOT NULL,
  `navn` varchar(200) NOT NULL,
  `tlf` varchar(8) NOT NULL,
  `epost` varchar(200) NOT NULL,
  `passord` varchar(200) NOT NULL,
  `isDriver` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `bruker`
--

INSERT INTO `bruker` (`brukerID`, `navn`, `tlf`, `epost`, `passord`, `isDriver`) VALUES
(1, 'Hans Hansen', '95458678', 'hans@test.com', '123456', 1),
(2, 'Per Askeladden', '95458679', 'per@test.com', '123456', 0),
(3, 'Pål Askeladden', '95478678', 'paal@test.com', '123456', 1),
(4, 'Espen Askeladden', '96458678', 'Espen@test.com', '123456', 1),
(6, 'Gro Harlem Bruntland', '45458678', 'gro@test.com', '123456', 0),
(7, 'Fritjof Nansen', '97458678', 'fritjof@test.com', '123456', 1),
(8, 'Kari Normann', '95758678', 'kari@test.com', '123456', 0),
(9, 'Jon Aalsgård', '95454678', 'jon@test.com', '123456', 0),
(10, 'Maria Mena', '48921375', 'maria@test.com', '123456', 0),
(11, 'Tone Damli', '95468678', 'tone@test.com', '123456', 1);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `kjoretur`
--

CREATE TABLE `kjoretur` (
  `kjoreturID` int(11) NOT NULL,
  `dato` date NOT NULL,
  `startTid` time NOT NULL,
  `sluttTid` time NOT NULL,
  `startSted` varchar(200) DEFAULT NULL,
  `sluttSted` varchar(200) NOT NULL,
  `kjentStopp` varchar(200) NOT NULL,
  `ledigplass` int(11) NOT NULL,
  `merke` varchar(200) NOT NULL,
  `modell` varchar(200) NOT NULL,
  `aarsmodell` varchar(4) DEFAULT NULL,
  `sykkelfeste` tinyint(1) NOT NULL DEFAULT '0',
  `takboks` tinyint(1) NOT NULL DEFAULT '0',
  `sjaaforNavn` varchar(200) NOT NULL,
  `sjaaforTlf` varchar(8) NOT NULL,
  `sjaaforEpost` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `kjoretur`
--

INSERT INTO `kjoretur` (`kjoreturID`, `dato`, `startTid`, `sluttTid`, `startSted`, `sluttSted`, `kjentStopp`, `ledigplass`, `merke`, `modell`, `aarsmodell`, `sykkelfeste`, `takboks`, `sjaaforNavn`, `sjaaforTlf`, `sjaaforEpost`) VALUES
(1, '2018-05-14', '08:00:00', '08:45:00', 'Notodden', 'Bø', 'Gvarv', 4, 'Mazda', '6', '2007', 0, 0, 'Pål Askeladden', '95478678', 'paal@test.com'),
(2, '2018-05-21', '09:00:00', '10:15:00', 'Skien', 'Bø', 'Ulefoss', 3, 'Honda', 'Prelude', '1992', 0, 0, 'Fritjof Nansen', '97458678', 'fritjof@test.com'),
(3, '2018-05-23', '15:00:00', '16:30:00', 'Bø', 'Kongsberg', 'Notodden', 2, 'Volkswagen', 'Passat', '2010', 1, 1, 'Tone Damli', '95468678', 'tone@test.com'),
(4, '2018-05-14', '08:00:00', '08:45:00', 'Høydalsmo', 'Bø', 'Seljord', 2, 'Volkswagen', 'Golf', '2012', 1, 0, 'Espen Askeladden', '96458678', 'Espen@test.com');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `passasjer`
--

CREATE TABLE `passasjer` (
  `passasjerID` int(11) NOT NULL,
  `brukerID` int(11) NOT NULL,
  `pickUpPoint` varchar(200) CHARACTER SET latin1 NOT NULL,
  `sisteFrist` time NOT NULL,
  `kjoreturID` int(11) NOT NULL,
  `isDriver` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dataark for tabell `passasjer`
--

INSERT INTO `passasjer` (`passasjerID`, `brukerID`, `pickUpPoint`, `sisteFrist`, `kjoreturID`, `isDriver`) VALUES
(1, 3, 'Gvarv', '12:15:00', 1, 1),
(2, 9, 'Akkerhaugen', '08:00:00', 1, 0),
(3, 6, 'Seljord', '08:15:00', 4, 0),
(4, 10, 'Ulefoss', '10:00:00', 2, 0),
(5, 7, 'Skien', '10:15:00', 2, 1),
(6, 11, 'Gvarv', '16:30:00', 3, 1),
(7, 4, 'Høydalsmo', '08:45:00', 4, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bruker`
--
ALTER TABLE `bruker`
  ADD PRIMARY KEY (`brukerID`);

--
-- Indexes for table `kjoretur`
--
ALTER TABLE `kjoretur`
  ADD PRIMARY KEY (`kjoreturID`);

--
-- Indexes for table `passasjer`
--
ALTER TABLE `passasjer`
  ADD PRIMARY KEY (`passasjerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bruker`
--
ALTER TABLE `bruker`
  MODIFY `brukerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `kjoretur`
--
ALTER TABLE `kjoretur`
  MODIFY `kjoreturID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `passasjer`
--
ALTER TABLE `passasjer`
  MODIFY `passasjerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
