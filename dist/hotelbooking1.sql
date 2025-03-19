-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2025 at 08:32 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelbooking1`
--

-- --------------------------------------------------------

--
-- Table structure for table `bfirst`
--
CREATE DATABASE hotelbooking1;
USE hotelbooking1;

CREATE TABLE `bfirst` (
  `ID` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bfirst`
--

INSERT INTO `bfirst` (`ID`, `price`) VALUES
('0', 0),
('1', 200),
('2', 300),
('3', 250),
('4', 300),
('5', 500);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `name` varchar(30) DEFAULT NULL,
  `NIC` varchar(30) DEFAULT NULL,
  `Address` varchar(30) DEFAULT NULL,
  `Tell_NO` varchar(30) DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  `month` varchar(30) NOT NULL,
  `year` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`name`, `NIC`, `Address`, `Tell_NO`, `date`, `month`, `year`) VALUES
(' viraj', ' 20012345678', ' colombo 5', ' 0714567902', '4', '3', '2025'),
(' bob', ' 123456', ' galle', ' 0981234567', '3', '2', '2025'),
('hiruni ', ' 20032346894', ' Kurunegala', ' 0721345908', '3', '2', '2026'),
(' nirmala', ' 198823456v', ' katugasthota', ' 0711123897', '4', '2', '2026'),
(' heshan', ' 123456', ' galle', ' 0723567834', '3', '5', ' '),
(' joshap', ' 124678v', ' panadura', ' 0723457890', '5', '3', '2025'),
(' dfgybb', ' 56677', ' gtjikok', ' 66788', '3', '3', '2025');

-- --------------------------------------------------------

--
-- Table structure for table `breakfast`
--

CREATE TABLE `breakfast` (
  `Name` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `breakfast`
--

INSERT INTO `breakfast` (`Name`, `price`) VALUES
('No_meal', 0),
('String_hoppers', 200),
('Milk_rice', 300),
('pittu', 250),
('Bread', 300),
('Noodles', 500);

-- --------------------------------------------------------

--
-- Table structure for table `breakfst`
--

CREATE TABLE `breakfst` (
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `breakfst`
--

INSERT INTO `breakfst` (`price`) VALUES
(0),
(200),
(300),
(250),
(300),
(500);

-- --------------------------------------------------------

--
-- Table structure for table `breakft`
--

CREATE TABLE `breakft` (
  `ID` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `breakft`
--

INSERT INTO `breakft` (`ID`, `price`) VALUES
('1', 200),
('2', 300),
('3', 250),
('4', 300),
('5', 500);

-- --------------------------------------------------------

--
-- Table structure for table `clrooms`
--

CREATE TABLE `clrooms` (
  `Room_typ` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clrooms`
--

INSERT INTO `clrooms` (`Room_typ`, `price`, `avlbl`) VALUES
('Single', 4000, 150),
('Double', 7000, 130),
('Family', 10000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `colomrooms`
--

CREATE TABLE `colomrooms` (
  `ID` int(30) DEFAULT NULL,
  `Room_typ` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `colomrooms`
--

INSERT INTO `colomrooms` (`ID`, `Room_typ`, `price`, `avlbl`) VALUES
(1, 'Single', 2500, 100),
(2, 'Double', 7000, 130),
(3, 'Family', 10000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `crooms`
--

CREATE TABLE `crooms` (
  `Room_typ` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `crooms`
--

INSERT INTO `crooms` (`Room_typ`, `price`, `avlbl`) VALUES
('Single', 4000, 150),
('Double', 7000, 130),
('Family', 10000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `dinr`
--

CREATE TABLE `dinr` (
  `ID` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dinr`
--

INSERT INTO `dinr` (`ID`, `price`) VALUES
('0', 0),
('1', 1250),
('2', 1500),
('3', 1000),
('4', 1350),
('5', 1250),
('6', 1000),
('7', 1500),
('8', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `glrooms`
--

CREATE TABLE `glrooms` (
  `ID` int(30) DEFAULT NULL,
  `Room_typ` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `glrooms`
--

INSERT INTO `glrooms` (`ID`, `Room_typ`, `price`, `avlbl`) VALUES
(1, 'Single', 3500, 150),
(2, 'Double', 7000, 95),
(3, 'Family', 7000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `lunc`
--

CREATE TABLE `lunc` (
  `ID` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lunc`
--

INSERT INTO `lunc` (`ID`, `price`) VALUES
('0', 0),
('1', 800),
('2', 1000),
('3', 1250),
('4', 1800);

-- --------------------------------------------------------

--
-- Table structure for table `lunch`
--

CREATE TABLE `lunch` (
  `ID` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lunch`
--

INSERT INTO `lunch` (`ID`, `price`) VALUES
('1', 800),
('2', 1000),
('3', 1250),
('4', 1800);

-- --------------------------------------------------------

--
-- Table structure for table `packages`
--

CREATE TABLE `packages` (
  `Package` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `packages`
--

INSERT INTO `packages` (`Package`, `Price`) VALUES
('Bread & Breakfast(B&B)', 2000),
('Half Board(HB)', 5000),
('Full Board(FB)', 8000),
('All  Inclusive(AI)', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `name` varchar(50) NOT NULL,
  `NIC` varchar(30) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `time` time NOT NULL,
  `guests` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`name`, `NIC`, `contact`, `checkin`, `checkout`, `time`, `guests`) VALUES
('somapala', '', '0721356789', '2025-03-03', '2025-03-07', '11:10:00', 3),
('Miran viraj', '', '0713456789', '2025-05-02', '2029-04-13', '18:48:00', 17),
('Gorge', '12345678901', '0712345678', '2025-03-08', '2025-03-08', '00:01:00', 1),
('Charly', '90909090909', '0712567802', '2025-04-08', '2026-05-10', '02:10:00', 1),
('jones', '57997686868', '0112356890', '2025-03-08', '2025-03-11', '02:44:00', 1),
('udayakumara', '75667890345', '0721356789', '2025-03-08', '2025-03-26', '14:01:00', 1),
('karunarathna', '12345678901', '0712356789', '2025-03-09', '2025-03-09', '21:38:00', 1),
('hhhh', '12345678901', '0712345678', '2025-04-10', '2025-04-13', '09:16:00', 3),
('hhghff', '12345678901', '0234567567', '2025-03-10', '2025-03-10', '09:26:00', 1),
('miran', '12345678901', '0712345678', '2025-03-11', '2025-03-12', '10:52:00', 5);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `Room_typ` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `Roomtyp` varchar(30) DEFAULT NULL,
  `price` int(30) DEFAULT NULL,
  `avlbl` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE `signup` (
  `Username` varchar(30) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`Username`, `Email`, `password`) VALUES
(' jhon', ' jhon@gmail.com', 'jm123'),
(' jhons', ' jns@gmail.com', 'jm123'),
(' dulamith', ' dola@gmail.com', 'dola123'),
(' frank', ' fr@gmail.com', 'fr123'),
(' ghj', ' dfgfg', 'fff'),
(' somapala', ' some@gmail.com', 'some@123'),
('nirmal', 'nirmal@gmail.com', '123n'),
('karl', 'kar@gmail.com', 'ka25'),
('dinuka', 'dinu@gmail.com', 'dinu123'),
('Rocky', 'rocky@gmail.com', 'Rocky123'),
('Karunarathna', 'karu@gmail.com', 'karu@123'),
('jhons', 'jk@gmail.com', '12345'),
('miran', 'miran@gmail.com', '1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
