-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 08 Mai 2020 à 14:59
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `reservation`
--

-- --------------------------------------------------------

--
-- Structure de la table `annulation`
--

CREATE TABLE IF NOT EXISTS `annulation` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `motif` varchar(100) NOT NULL,
  `idsejour` int(20) NOT NULL,
  `idclient` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `motif` (`motif`),
  KEY `idsejour` (`idsejour`),
  KEY `idclient` (`idclient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `associationgroupesejour`
--

CREATE TABLE IF NOT EXISTS `associationgroupesejour` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `prix_unitaire` int(20) NOT NULL,
  `groupe` int(20) NOT NULL,
  `id_sejour` int(20) NOT NULL,
  `nbplace` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `groupe` (`groupe`),
  KEY `sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Contenu de la table `associationgroupesejour`
--

INSERT INTO `associationgroupesejour` (`id`, `prix_unitaire`, `groupe`, `id_sejour`, `nbplace`) VALUES
(33, 100, 17, 17, 20);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution`
--

INSERT INTO `batch_job_execution` (`JOB_EXECUTION_ID`, `VERSION`, `JOB_INSTANCE_ID`, `CREATE_TIME`, `START_TIME`, `END_TIME`, `STATUS`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`, `JOB_CONFIGURATION_LOCATION`) VALUES
(1, 2, 1, '2020-03-10 12:19:37', '2020-03-10 12:19:37', '2020-03-10 12:19:38', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:19:38', NULL),
(2, 2, 2, '2020-03-10 12:24:10', '2020-03-10 12:24:10', '2020-03-10 12:24:10', 'FAILED', 'FAILED', '', '2020-03-10 12:24:10', NULL),
(3, 2, 3, '2020-03-10 12:24:42', '2020-03-10 12:24:42', '2020-03-10 12:24:42', 'FAILED', 'FAILED', '', '2020-03-10 12:24:42', NULL),
(4, 2, 4, '2020-03-10 12:25:22', '2020-03-10 12:25:22', '2020-03-10 12:25:23', 'FAILED', 'FAILED', '', '2020-03-10 12:25:23', NULL),
(5, 2, 5, '2020-03-10 12:25:54', '2020-03-10 12:25:54', '2020-03-10 12:25:54', 'FAILED', 'FAILED', '', '2020-03-10 12:25:54', NULL),
(6, 2, 6, '2020-03-10 12:26:20', '2020-03-10 12:26:20', '2020-03-10 12:26:21', 'FAILED', 'FAILED', '', '2020-03-10 12:26:21', NULL),
(7, 2, 7, '2020-03-10 12:26:53', '2020-03-10 12:26:53', '2020-03-10 12:26:53', 'FAILED', 'FAILED', '', '2020-03-10 12:26:53', NULL),
(8, 2, 8, '2020-03-10 12:27:48', '2020-03-10 12:27:48', '2020-03-10 12:27:49', 'FAILED', 'FAILED', '', '2020-03-10 12:27:49', NULL),
(9, 2, 9, '2020-03-10 12:28:38', '2020-03-10 12:28:38', '2020-03-10 12:28:38', 'FAILED', 'FAILED', '', '2020-03-10 12:28:38', NULL),
(10, 2, 10, '2020-03-10 12:29:55', '2020-03-10 12:29:55', '2020-03-10 12:29:55', 'FAILED', 'FAILED', '', '2020-03-10 12:29:55', NULL),
(11, 2, 11, '2020-03-10 12:31:21', '2020-03-10 12:31:21', '2020-03-10 12:31:21', 'FAILED', 'FAILED', '', '2020-03-10 12:31:21', NULL),
(12, 2, 12, '2020-03-10 12:32:02', '2020-03-10 12:32:02', '2020-03-10 12:32:02', 'FAILED', 'FAILED', '', '2020-03-10 12:32:02', NULL),
(13, 2, 13, '2020-03-10 12:34:05', '2020-03-10 12:34:06', '2020-03-10 12:34:06', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:34:06', NULL),
(14, 2, 14, '2020-03-10 12:34:26', '2020-03-10 12:34:27', '2020-03-10 12:34:27', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:34:27', NULL),
(15, 2, 15, '2020-03-10 12:37:38', '2020-03-10 12:37:38', '2020-03-10 12:37:38', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:37:38', NULL),
(16, 2, 16, '2020-03-10 12:37:49', '2020-03-10 12:37:49', '2020-03-10 12:37:49', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:37:49', NULL),
(17, 2, 17, '2020-03-10 12:39:05', '2020-03-10 12:39:05', '2020-03-10 12:39:06', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:39:06', NULL),
(18, 2, 18, '2020-03-10 12:39:18', '2020-03-10 12:39:18', '2020-03-10 12:39:18', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:39:18', NULL),
(19, 2, 19, '2020-03-10 12:43:20', '2020-03-10 12:43:20', '2020-03-10 12:43:20', 'FAILED', 'FAILED', '', '2020-03-10 12:43:20', NULL),
(20, 2, 20, '2020-03-10 12:44:40', '2020-03-10 12:44:40', '2020-03-10 12:44:40', 'FAILED', 'FAILED', '', '2020-03-10 12:44:40', NULL),
(21, 2, 21, '2020-03-10 12:45:45', '2020-03-10 12:45:46', '2020-03-10 12:45:46', 'FAILED', 'FAILED', '', '2020-03-10 12:45:46', NULL),
(22, 2, 22, '2020-03-10 12:46:48', '2020-03-10 12:46:48', '2020-03-10 12:46:49', 'COMPLETED', 'COMPLETED', '', '2020-03-10 12:46:49', NULL),
(23, 2, 23, '2020-03-10 22:26:53', '2020-03-10 22:26:54', '2020-03-10 22:26:54', 'FAILED', 'FAILED', '', '2020-03-10 22:26:54', NULL),
(24, 2, 24, '2020-03-10 22:27:26', '2020-03-10 22:27:26', '2020-03-10 22:27:27', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:27:27', NULL),
(25, 2, 25, '2020-03-10 22:27:40', '2020-03-10 22:27:40', '2020-03-10 22:27:40', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:27:40', NULL),
(26, 2, 26, '2020-03-10 22:34:40', '2020-03-10 22:34:40', '2020-03-10 22:34:41', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:34:41', NULL),
(27, 2, 27, '2020-03-10 22:35:51', '2020-03-10 22:35:51', '2020-03-10 22:35:51', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:35:51', NULL),
(28, 2, 28, '2020-03-10 22:36:06', '2020-03-10 22:36:06', '2020-03-10 22:36:07', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:36:07', NULL),
(29, 2, 29, '2020-03-10 22:36:36', '2020-03-10 22:36:36', '2020-03-10 22:36:37', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:36:37', NULL),
(30, 2, 30, '2020-03-10 22:38:05', '2020-03-10 22:38:05', '2020-03-10 22:38:05', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:38:05', NULL),
(31, 2, 31, '2020-03-10 22:38:25', '2020-03-10 22:38:25', '2020-03-10 22:38:25', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:38:25', NULL),
(32, 2, 32, '2020-03-10 22:38:57', '2020-03-10 22:38:57', '2020-03-10 22:38:57', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:38:57', NULL),
(33, 2, 33, '2020-03-10 22:42:41', '2020-03-10 22:42:41', '2020-03-10 22:42:42', 'COMPLETED', 'COMPLETED', '', '2020-03-10 22:42:42', NULL),
(34, 2, 34, '2020-03-11 13:25:17', '2020-03-11 13:25:17', '2020-03-11 13:25:17', 'COMPLETED', 'COMPLETED', '', '2020-03-11 13:25:17', NULL),
(35, 2, 35, '2020-03-11 13:35:03', '2020-03-11 13:35:03', '2020-03-11 13:35:04', 'COMPLETED', 'COMPLETED', '', '2020-03-11 13:35:04', NULL),
(36, 2, 36, '2020-03-11 13:35:28', '2020-03-11 13:35:28', '2020-03-11 13:35:28', 'COMPLETED', 'COMPLETED', '', '2020-03-11 13:35:28', NULL),
(37, 2, 37, '2020-03-11 13:35:42', '2020-03-11 13:35:42', '2020-03-11 13:35:42', 'COMPLETED', 'COMPLETED', '', '2020-03-11 13:35:42', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_context`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution_context`
--

INSERT INTO `batch_job_execution_context` (`JOB_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(1, '{}', NULL),
(2, '{}', NULL),
(3, '{}', NULL),
(4, '{}', NULL),
(5, '{}', NULL),
(6, '{}', NULL),
(7, '{}', NULL),
(8, '{}', NULL),
(9, '{}', NULL),
(10, '{}', NULL),
(11, '{}', NULL),
(12, '{}', NULL),
(13, '{}', NULL),
(14, '{}', NULL),
(15, '{}', NULL),
(16, '{}', NULL),
(17, '{}', NULL),
(18, '{}', NULL),
(19, '{}', NULL),
(20, '{}', NULL),
(21, '{}', NULL),
(22, '{}', NULL),
(23, '{}', NULL),
(24, '{}', NULL),
(25, '{}', NULL),
(26, '{}', NULL),
(27, '{}', NULL),
(28, '{}', NULL),
(29, '{}', NULL),
(30, '{}', NULL),
(31, '{}', NULL),
(32, '{}', NULL),
(33, '{}', NULL),
(34, '{}', NULL),
(35, '{}', NULL),
(36, '{}', NULL),
(37, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_params`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution_params`
--

INSERT INTO `batch_job_execution_params` (`JOB_EXECUTION_ID`, `TYPE_CD`, `KEY_NAME`, `STRING_VAL`, `DATE_VAL`, `LONG_VAL`, `DOUBLE_VAL`, `IDENTIFYING`) VALUES
(1, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 1, 0, 'Y'),
(2, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 2, 0, 'Y'),
(3, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 3, 0, 'Y'),
(4, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 4, 0, 'Y'),
(5, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 5, 0, 'Y'),
(6, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 6, 0, 'Y'),
(7, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 7, 0, 'Y'),
(8, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 8, 0, 'Y'),
(9, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 9, 0, 'Y'),
(10, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 10, 0, 'Y'),
(11, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 11, 0, 'Y'),
(12, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 12, 0, 'Y'),
(13, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 13, 0, 'Y'),
(14, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 14, 0, 'Y'),
(15, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 15, 0, 'Y'),
(16, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 16, 0, 'Y'),
(17, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 17, 0, 'Y'),
(18, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 18, 0, 'Y'),
(19, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 19, 0, 'Y'),
(20, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 20, 0, 'Y'),
(21, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 21, 0, 'Y'),
(22, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 22, 0, 'Y'),
(23, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 23, 0, 'Y'),
(24, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 24, 0, 'Y'),
(25, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 25, 0, 'Y'),
(26, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 26, 0, 'Y'),
(27, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 27, 0, 'Y'),
(28, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 28, 0, 'Y'),
(29, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 29, 0, 'Y'),
(30, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 30, 0, 'Y'),
(31, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 31, 0, 'Y'),
(32, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 32, 0, 'Y'),
(33, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 33, 0, 'Y'),
(34, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 34, 0, 'Y'),
(35, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 35, 0, 'Y'),
(36, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 36, 0, 'Y'),
(37, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 37, 0, 'Y');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_seq`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution_seq`
--

INSERT INTO `batch_job_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(37, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_instance`
--

CREATE TABLE IF NOT EXISTS `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_instance`
--

INSERT INTO `batch_job_instance` (`JOB_INSTANCE_ID`, `VERSION`, `JOB_NAME`, `JOB_KEY`) VALUES
(1, 0, 'exportUserJob', '853d3449e311f40366811cbefb3d93d7'),
(2, 0, 'exportUserJob', 'e070bff4379694c0210a51d9f6c6a564'),
(3, 0, 'exportUserJob', 'a3364faf893276dea0caacefbf618db5'),
(4, 0, 'exportUserJob', '47c0a8118b74165a864b66d37c7b6cf5'),
(5, 0, 'exportUserJob', 'ce148f5f9c2bf4dc9bd44a7a5f64204c'),
(6, 0, 'exportUserJob', 'bd0034040292bc81e6ccac0e25d9a578'),
(7, 0, 'exportUserJob', '597815c7e4ab1092c1b25130aae725cb'),
(8, 0, 'exportUserJob', 'f55a96b11012be4fcfb6cf005435182d'),
(9, 0, 'exportUserJob', '96a5ed9bac43e779455f3e71c0f64840'),
(10, 0, 'exportUserJob', '1aac4f3e74894b78fa3ce5d8a25e1ef0'),
(11, 0, 'exportUserJob', '604bbfc4c68cb1f903780c2853ad4801'),
(12, 0, 'exportUserJob', '556ebe34220b4032509f2581356ba47c'),
(13, 0, 'exportUserJob', 'edc440efb5ddd2a3b2622f16a12bf105'),
(14, 0, 'exportUserJob', 'f3d5e568c384ee72cba8bc6a51057fe4'),
(15, 0, 'exportUserJob', '378ef1ecb81cf9edac4ab119bdab9d9d'),
(16, 0, 'exportUserJob', 'e073471cc312cadef424c3be7915c0af'),
(17, 0, 'exportUserJob', '46ba78a99abf1e2fba4a8861749d7572'),
(18, 0, 'exportUserJob', 'b88d31b704adf9f94fe9d4ccff795708'),
(19, 0, 'exportUserJob', '64d4e6d635ee3ad949314224afce46c2'),
(20, 0, 'exportUserJob', '75c16c09800a944220a789de10278de0'),
(21, 0, 'exportUserJob', '1b759d32440acdcbf90da6919b5d16ad'),
(22, 0, 'exportUserJob', '1f995cec4b562af773a2e473c369f069'),
(23, 0, 'exportUserJob', '42106293a859255c2b210d04a51240ca'),
(24, 0, 'exportUserJob', '9799b3a84f4d6f15a5e8c11360e7387b'),
(25, 0, 'exportUserJob', '6eecb29840a845c35cfa9b2da21862f9'),
(26, 0, 'exportUserJob', 'e465389b77512db6f30ed6a3b7a9682c'),
(27, 0, 'exportUserJob', '19be35489361f0d498838921e450c4cb'),
(28, 0, 'exportUserJob', '20744cb6ca7f8dc12940aa7fd8f89763'),
(29, 0, 'exportUserJob', 'a3bcf78496166aaf18ec0c14120074d6'),
(30, 0, 'exportUserJob', '890787fe3f8be2564cf02f516acdac28'),
(31, 0, 'exportUserJob', '5b65175918977eab4addf511ccceb909'),
(32, 0, 'exportUserJob', '9c9fce01479d33c09c00ad7ef17a0fa7'),
(33, 0, 'exportUserJob', '4e2d614992aa7a22b11fc1d32b1e3000'),
(34, 0, 'exportUserJob', '50431ec8d19c121d87fbf96bef0b712f'),
(35, 0, 'exportUserJob', '31a37b7e33e41f6cb941c4d363fcf2ca'),
(36, 0, 'exportUserJob', '56d75bdddea05ee8f2ed823177214afe'),
(37, 0, 'exportUserJob', '90a4c5eeeb198a44066f972c03932729');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_seq`
--

CREATE TABLE IF NOT EXISTS `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_seq`
--

INSERT INTO `batch_job_seq` (`ID`, `UNIQUE_KEY`) VALUES
(37, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_step_execution`
--

INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`, `VERSION`, `STEP_NAME`, `JOB_EXECUTION_ID`, `START_TIME`, `END_TIME`, `STATUS`, `COMMIT_COUNT`, `READ_COUNT`, `FILTER_COUNT`, `WRITE_COUNT`, `READ_SKIP_COUNT`, `WRITE_SKIP_COUNT`, `PROCESS_SKIP_COUNT`, `ROLLBACK_COUNT`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`) VALUES
(1, 3, 'step1', 1, '2020-03-10 12:19:37', '2020-03-10 12:19:38', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:19:38'),
(2, 2, 'step1', 2, '2020-03-10 12:24:10', '2020-03-10 12:24:10', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2020-03-10 12:24:10'),
(3, 2, 'step1', 3, '2020-03-10 12:24:42', '2020-03-10 12:24:42', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2020-03-10 12:24:42'),
(4, 2, 'step1', 4, '2020-03-10 12:25:22', '2020-03-10 12:25:23', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2020-03-10 12:25:23'),
(5, 2, 'step1', 5, '2020-03-10 12:25:54', '2020-03-10 12:25:54', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2020-03-10 12:25:54'),
(6, 2, 'step1', 6, '2020-03-10 12:26:21', '2020-03-10 12:26:21', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:26:21'),
(7, 2, 'step1', 7, '2020-03-10 12:26:53', '2020-03-10 12:26:53', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:26:53'),
(8, 2, 'step1', 8, '2020-03-10 12:27:48', '2020-03-10 12:27:49', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:27:49'),
(9, 2, 'step1', 9, '2020-03-10 12:28:38', '2020-03-10 12:28:38', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:28:38'),
(10, 2, 'step1', 10, '2020-03-10 12:29:55', '2020-03-10 12:29:55', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:29:55'),
(11, 2, 'step1', 11, '2020-03-10 12:31:21', '2020-03-10 12:31:21', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:31:21'),
(12, 2, 'step1', 12, '2020-03-10 12:32:02', '2020-03-10 12:32:02', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''id '' of bean class [com.example.demo.batch.modele.User]: Bean property ''id '' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step', '2020-03-10 12:32:02'),
(13, 3, 'step1', 13, '2020-03-10 12:34:06', '2020-03-10 12:34:06', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:34:06'),
(14, 3, 'step1', 14, '2020-03-10 12:34:27', '2020-03-10 12:34:27', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:34:27'),
(15, 3, 'step1', 15, '2020-03-10 12:37:38', '2020-03-10 12:37:38', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:37:38'),
(16, 3, 'step1', 16, '2020-03-10 12:37:49', '2020-03-10 12:37:49', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:37:49'),
(17, 3, 'step1', 17, '2020-03-10 12:39:05', '2020-03-10 12:39:05', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:39:05'),
(18, 3, 'step1', 18, '2020-03-10 12:39:18', '2020-03-10 12:39:18', 'COMPLETED', 1, 3, 0, 3, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:39:18'),
(19, 2, 'step1', 19, '2020-03-10 12:43:20', '2020-03-10 12:43:20', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''evenementa'' of bean class [com.example.demo.batch.modele.Evenement]: Bean property ''evenementa'' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframew', '2020-03-10 12:43:20'),
(20, 2, 'step1', 20, '2020-03-10 12:44:40', '2020-03-10 12:44:40', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''evenementa'' of bean class [com.example.demo.batch.modele.Evenement]: Bean property ''evenementa'' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframew', '2020-03-10 12:44:40'),
(21, 2, 'step1', 21, '2020-03-10 12:45:46', '2020-03-10 12:45:46', 'FAILED', 0, 8, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.beans.NotReadablePropertyException: Invalid property ''dateevenement'' of bean class [com.example.demo.batch.modele.Evenement]: Bean property ''dateevenement'' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:622)\r\n	at org.springframework.beans.AbstractNestablePropertyAccessor.getPropertyValue(AbstractNestablePropertyAccessor.java:612)\r\n	at org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor.extract(BeanWrapperFieldExtractor.java:57)\r\n	at org.springframework.batch.item.file.transform.ExtractorLineAggregator.aggregate(ExtractorLineAggregator.java:54)\r\n	at org.springframework.batch.item.file.FlatFileItemWriter.doWrite(FlatFileItemWriter.java:78)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:247)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.spring', '2020-03-10 12:45:46'),
(22, 3, 'step1', 22, '2020-03-10 12:46:48', '2020-03-10 12:46:49', 'COMPLETED', 1, 8, 0, 8, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 12:46:49'),
(23, 2, 'step1', 23, '2020-03-10 22:26:54', '2020-03-10 22:26:54', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2020-03-10 22:26:54'),
(24, 3, 'step1', 24, '2020-03-10 22:27:27', '2020-03-10 22:27:27', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:27:27'),
(25, 3, 'step1', 25, '2020-03-10 22:27:40', '2020-03-10 22:27:40', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:27:40'),
(26, 3, 'step1', 26, '2020-03-10 22:34:40', '2020-03-10 22:34:41', 'COMPLETED', 1, 1, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:34:41'),
(27, 3, 'step1', 27, '2020-03-10 22:35:51', '2020-03-10 22:35:51', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:35:51'),
(28, 3, 'step1', 28, '2020-03-10 22:36:06', '2020-03-10 22:36:07', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:36:07'),
(29, 3, 'step1', 29, '2020-03-10 22:36:37', '2020-03-10 22:36:37', 'COMPLETED', 1, 1, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:36:37'),
(30, 3, 'step1', 30, '2020-03-10 22:38:05', '2020-03-10 22:38:05', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:38:05'),
(31, 3, 'step1', 31, '2020-03-10 22:38:25', '2020-03-10 22:38:25', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:38:25'),
(32, 3, 'step1', 32, '2020-03-10 22:38:57', '2020-03-10 22:38:57', 'COMPLETED', 1, 1, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:38:57'),
(33, 3, 'step1', 33, '2020-03-10 22:42:42', '2020-03-10 22:42:42', 'COMPLETED', 1, 1, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-10 22:42:42'),
(34, 3, 'step1', 34, '2020-03-11 13:25:17', '2020-03-11 13:25:17', 'COMPLETED', 1, 9, 0, 9, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-11 13:25:17'),
(35, 3, 'step1', 35, '2020-03-11 13:35:03', '2020-03-11 13:35:04', 'COMPLETED', 1, 9, 0, 9, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-11 13:35:04'),
(36, 3, 'step1', 36, '2020-03-11 13:35:28', '2020-03-11 13:35:28', 'COMPLETED', 1, 0, 0, 0, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-11 13:35:28'),
(37, 3, 'step1', 37, '2020-03-11 13:35:42', '2020-03-11 13:35:42', 'COMPLETED', 1, 1, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-03-11 13:35:42');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_context`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_step_execution_context`
--

INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(1, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(2, '{}', NULL),
(3, '{}', NULL),
(4, '{}', NULL),
(5, '{}', NULL),
(6, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(7, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(8, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(9, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(10, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(11, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(12, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(13, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(14, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(15, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(16, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(17, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(18, '{"FlatFileItemWriter.current.count":["java.lang.Long",72],"JdbcCursorItemReader.read.count":4,"FlatFileItemWriter.written":["java.lang.Long",3],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(19, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(20, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(21, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":0,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(22, '{"FlatFileItemWriter.current.count":["java.lang.Long",428],"JdbcCursorItemReader.read.count":9,"FlatFileItemWriter.written":["java.lang.Long",8],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(23, '{}', NULL),
(24, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(25, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(26, '{"FlatFileItemWriter.current.count":["java.lang.Long",62],"JdbcCursorItemReader.read.count":2,"FlatFileItemWriter.written":["java.lang.Long",1],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(27, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(28, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(29, '{"FlatFileItemWriter.current.count":["java.lang.Long",62],"JdbcCursorItemReader.read.count":2,"FlatFileItemWriter.written":["java.lang.Long",1],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(30, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(31, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(32, '{"FlatFileItemWriter.current.count":["java.lang.Long",51],"JdbcCursorItemReader.read.count":2,"FlatFileItemWriter.written":["java.lang.Long",1],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(33, '{"FlatFileItemWriter.current.count":["java.lang.Long",51],"JdbcCursorItemReader.read.count":2,"FlatFileItemWriter.written":["java.lang.Long",1],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(34, '{"FlatFileItemWriter.current.count":["java.lang.Long",477],"JdbcCursorItemReader.read.count":10,"FlatFileItemWriter.written":["java.lang.Long",9],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(35, '{"FlatFileItemWriter.current.count":["java.lang.Long",477],"JdbcCursorItemReader.read.count":10,"FlatFileItemWriter.written":["java.lang.Long",9],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(36, '{"FlatFileItemWriter.current.count":["java.lang.Long",0],"JdbcCursorItemReader.read.count":1,"FlatFileItemWriter.written":["java.lang.Long",0],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL),
(37, '{"FlatFileItemWriter.current.count":["java.lang.Long",63],"JdbcCursorItemReader.read.count":2,"FlatFileItemWriter.written":["java.lang.Long",1],"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_seq`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_step_execution_seq`
--

INSERT INTO `batch_step_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(37, '0');

-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

CREATE TABLE IF NOT EXISTS `centre` (
  `id_centre` int(10) NOT NULL AUTO_INCREMENT,
  `nom_centre` varchar(30) NOT NULL,
  `capacite` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_centre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `centre`
--

INSERT INTO `centre` (`id_centre`, `nom_centre`, `capacite`) VALUES
(9, 'combloux', 100),
(10, 'mangeroux', 100),
(11, 'abc', 1200);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(20) NOT NULL,
  `prenom_client` varchar(20) NOT NULL,
  `groupe_client` int(10) NOT NULL,
  `numero` varchar(10) NOT NULL DEFAULT '0',
  `observation` varchar(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `adresse` varchar(80) NOT NULL DEFAULT '',
  `code_postale` varchar(20) NOT NULL DEFAULT '""',
  `datenaissance` varchar(15) NOT NULL DEFAULT '""',
  `sexe` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groupe_client` (`groupe_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id`, `nom_client`, `prenom_client`, `groupe_client`, `numero`, `observation`, `email`, `adresse`, `code_postale`, `datenaissance`, `sexe`) VALUES
(1, 'test', 'test', 18, '0495334', '56', 'A@outlook.fr', '69bd', '45899', '2020-04-17', 'Homme'),
(2, 'jon', 'dupont', 18, '0593894503', 'MR jones', 'A@outlook.fr', '45 bd jean rostand', '45800', '2009-04-19', 'Homme'),
(3, 'mathieu', 'colombe', 18, '0693839453', 'MR JONES', 'a@outlook.fr', '49 bd jean rostand', '45800', '2010-04-10', 'Homme'),
(4, 'jonas', 'jones', 18, '0689321345', 'MR jones', 'mr@outlook.fr', '49bd jean rostand', '45800', '2009-04-12', 'Homme');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `codeclient` varchar(20) NOT NULL,
  `codesejour` varchar(20) NOT NULL,
  `evenementa` varchar(30) NOT NULL,
  `somme` varchar(30) NOT NULL,
  `dateevenement` varchar(100) NOT NULL,
  `methode` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `codeclient` (`codeclient`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`id`, `codeclient`, `codesejour`, `evenementa`, `somme`, `dateevenement`, `methode`) VALUES
(59, 'VAC500', '15', 'paiement groupe mairie', '300', '20-02-2020', 'CarteBleu'),
(60, 'VAC500', '26', 'paiement', '300', '20-02-2020', 'Cheque'),
(61, 'VAC500', '15', 'paiement groupe mairie', '15700', '20-02-2020', 'Cheque'),
(62, 'VAC500', '26', 'paiement', '200', '20-02-2020', 'CarteBleu'),
(63, 'vac200', '26', 'inscription', '100', '25-02-2020', 'Cheque'),
(64, 'vac200', '458f', 'inscription', '100', '28-02-2020', 'Cheque'),
(65, 'vac200', '18', 'paiement groupe mairie', '4000', '29-02-2020', 'CarteBleu'),
(66, 'vac2000', '49f', 'paiement', '100', '09-03-2020', 'CarteBleue'),
(67, 'vac600', '49f', 'inscription', '100', '10-03-2020', 'Cheque'),
(68, 'VAC500', '49f', 'inscription', '100', '12-04-2020', 'CarteBleue');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` int(10) NOT NULL AUTO_INCREMENT,
  `nom_groupe` varchar(20) NOT NULL,
  `tiers` varchar(10) NOT NULL DEFAULT '""',
  `commune` varchar(50) NOT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`id_groupe`, `nom_groupe`, `tiers`, `commune`) VALUES
(18, 'Mairie d evry', 'VAC500', 'Evry');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_sejour_client`
--

CREATE TABLE IF NOT EXISTS `groupe_sejour_client` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_groupe` int(20) NOT NULL,
  `id_sejour` int(20) NOT NULL,
  `id_client` int(20) NOT NULL,
  `depart` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_groupe` (`id_groupe`),
  KEY `id_sejour` (`id_sejour`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Contenu de la table `groupe_sejour_client`
--

INSERT INTO `groupe_sejour_client` (`id`, `id_groupe`, `id_sejour`, `id_client`, `depart`) VALUES
(8, 9, 15, 12, 'Orleans'),
(9, 9, 15, 13, 'Orleans'),
(10, 9, 15, 14, 'Orleans'),
(11, 11, 15, 17, 'Orleans'),
(12, 10, 16, 18, 'Orleans'),
(13, 15, 18, 21, 'Orleans'),
(14, 15, 18, 22, 'Orleans'),
(15, 15, 18, 23, 'Orleans'),
(16, 15, 18, 24, 'Orleans'),
(17, 15, 18, 25, 'Orleans');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE IF NOT EXISTS `inscription` (
  `id_inscription` int(10) NOT NULL AUTO_INCREMENT,
  `paiement` varchar(30) NOT NULL,
  `date_inscription` varchar(20) NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  `depart` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id_inscription`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `inscription`
--

INSERT INTO `inscription` (`id_inscription`, `paiement`, `date_inscription`, `code_client`, `id_sejour`, `depart`) VALUES
(1, '100', '12-04-2020', 4, 17, 'Orleans');

-- --------------------------------------------------------

--
-- Structure de la table `paiement_mairie`
--

CREATE TABLE IF NOT EXISTS `paiement_mairie` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `groupe` varchar(30) NOT NULL,
  `paiement` varchar(30) NOT NULL,
  `sejour` varchar(30) NOT NULL,
  `methode` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `paiement_mairie`
--

INSERT INTO `paiement_mairie` (`id`, `groupe`, `paiement`, `sejour`, `methode`) VALUES
(8, '9', '300', '15', 'CarteBleu'),
(9, '9', '15700', '15', 'Cheque'),
(10, '15', '4000', '18', 'CarteBleu');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int(10) NOT NULL AUTO_INCREMENT,
  `date_reservation` varchar(20) NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  `depart` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id_reservation`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `date_reservation`, `code_client`, `id_sejour`, `depart`) VALUES
(1, '12-04-2020 ', 1, 17, 'Orleans');

-- --------------------------------------------------------

--
-- Structure de la table `reservationgroupe`
--

CREATE TABLE IF NOT EXISTS `reservationgroupe` (
  `id` int(30) DEFAULT NULL,
  `sejour` int(30) NOT NULL,
  `groupe` int(30) NOT NULL,
  `place` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sejour`
--

CREATE TABLE IF NOT EXISTS `sejour` (
  `id_sejour` int(11) NOT NULL AUTO_INCREMENT,
  `duree` int(11) NOT NULL,
  `date_debut` varchar(10) NOT NULL,
  `date_fin` varchar(10) NOT NULL,
  `type_sejour` varchar(40) NOT NULL,
  `centre_id` int(11) NOT NULL,
  `prix` int(5) NOT NULL DEFAULT '0',
  `age_min` int(3) NOT NULL DEFAULT '0',
  `age_max` int(3) NOT NULL DEFAULT '0',
  `capacite` int(2) NOT NULL DEFAULT '0',
  `ref_sejour` varchar(15) NOT NULL DEFAULT '""',
  `numero` varchar(25) NOT NULL,
  PRIMARY KEY (`id_sejour`),
  KEY `centre_id` (`centre_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Contenu de la table `sejour`
--

INSERT INTO `sejour` (`id_sejour`, `duree`, `date_debut`, `date_fin`, `type_sejour`, `centre_id`, `prix`, `age_min`, `age_max`, `capacite`, `ref_sejour`, `numero`) VALUES
(17, 21, '2020-03-05', '2020-03-26', 'testi', 9, 200, 10, 13, 100, '49f', '49345DSDF');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `type`) VALUES
(1, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `userr`
--

CREATE TABLE IF NOT EXISTS `userr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `userr`
--

INSERT INTO `userr` (`id`, `name`) VALUES
(1, 'Jack Rutorial demo 1'),
(2, 'Jack Rutorial demo 2'),
(3, 'Jack Rutorial demo 3');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `annulation`
--
ALTER TABLE `annulation`
  ADD CONSTRAINT `annulation_ibfk_1` FOREIGN KEY (`idsejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `annulation_ibfk_2` FOREIGN KEY (`idclient`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `batch_job_execution`
--
ALTER TABLE `batch_job_execution`
  ADD CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`);

--
-- Contraintes pour la table `batch_job_execution_context`
--
ALTER TABLE `batch_job_execution_context`
  ADD CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_job_execution_params`
--
ALTER TABLE `batch_job_execution_params`
  ADD CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_step_execution`
--
ALTER TABLE `batch_step_execution`
  ADD CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_step_execution_context`
--
ALTER TABLE `batch_step_execution_context`
  ADD CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`groupe_client`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`code_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`id_sejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`code_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_sejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sejour`
--
ALTER TABLE `sejour`
  ADD CONSTRAINT `sejour_ibfk_1` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`id_centre`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
