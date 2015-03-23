-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 19, 2015 at 09:10 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `depense`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `categories_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`categories_id`, `categories_name`) VALUES
(1, 'bank'),
(2, 'Voiture');

-- --------------------------------------------------------

--
-- Table structure for table `depense`
--

CREATE TABLE IF NOT EXISTS `depense` (
  `id_depense` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `depense` int(11) NOT NULL,
  `date_depense` timestamp NOT NULL,
  PRIMARY KEY (`id_depense`),
  KEY `id_user` (`id_user`),
  KEY `id_category` (`id_category`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `depense`
--

INSERT INTO `depense` (`id_depense`, `id_category`, `id_user`, `depense`, `date_depense`) VALUES
(1, 1, 3, 40000, '2015-02-17 21:15:35'),
(2, 2, 4, 130000, '2015-02-17 21:16:27'),
(3, 1, 1, 30000, '2015-02-09 21:00:00'),
(4, 1, 3, 5000, '2015-02-10 21:00:00'),
(5, 1, 3, 5000, '2015-02-12 21:00:00'),
(6, 2, 3, 7000, '2015-03-18 21:00:00'),
(7, 1, 3, 500, '2015-03-25 21:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `groupuser`
--

CREATE TABLE IF NOT EXISTS `groupuser` (
  `group_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`group_name`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupuser`
--

INSERT INTO `groupuser` (`group_name`, `user_id`) VALUES
('admin', 1),
('others', 3),
('others', 4),
('others', 5),
('others', 6),
('others', 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_password`) VALUES
(1, 'admin', 'bdb8761b4d26ddfa8b74f144c07bd15'),
(3, 'haya', 'bdb8761b4d26ddfa8b74f144c07bd15'),
(4, 'others', 'bdb8761b4d26ddfa8b74f144c07bd15'),
(5, 'r', '202cb962ac59075b964b07152d234b70'),
(6, 'hayat', 'e10adc3949ba59abbe56e057f20f883e'),
(7, 'yi', 'bdb8761b4d26ddfa8b74f144c07bd15f');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `depense`
--
ALTER TABLE `depense`
  ADD CONSTRAINT `depense_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `depense_ibfk_3` FOREIGN KEY (`id_category`) REFERENCES `categories` (`categories_id`);

--
-- Constraints for table `groupuser`
--
ALTER TABLE `groupuser`
  ADD CONSTRAINT `groupuser_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
