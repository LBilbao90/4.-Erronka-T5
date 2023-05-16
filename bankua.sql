-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2023 a las 09:47:50
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bankua`
--
CREATE DATABASE IF NOT EXISTS `bankua` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bankua`;

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `bezeroEzabaketa` (`nan_bez` CHAR(9))   BEGIN
	DECLARE fin bool default 0;
	Declare kod_iban char(24);
	Declare kod_txartel char(16);

DECLARE c CURSOR FOR select iban, id_txartela from kudeatu where nan=nan_bez;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
OPEN c;
FETCH c INTO kod_iban,kod_txartel;
WHILE fin = 0 DO
    Delete from kontuBankario where iban=kod_iban;
    Delete from txartela where id_txartela=kod_txartel; 
    FETCH c INTO kod_iban,kod_txartel;
END WHILE;
CLOSE c;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insBezero` (`ins_Nan` CHAR(9), `ins_Izena` VARCHAR(20), `ins_Abizenak` VARCHAR(30), `ins_JaiotzeData` DATE, `ins_Sexua` ENUM('gizona','emakumea'), `ins_Telefonoa` CHAR(9), `ins_Pasahitza` VARCHAR(30))   BEGIN
DECLARE bez CHAR(9);
SELECT NAN INTO bez FROM bezeroa WHERE NAN = ins_Nan;
  IF (bez IS NOT NULL) THEN
    SELECT concat ('NAN hau erregistratuta dago');
  END IF;
 IF LENGTH(ins_Telefonoa) != 9 THEN
    SELECT concat ('Telefonoa 9 zenbaki izan behar ditu')ERROR;
  END IF;
 IF LENGTH(ins_Nan ) != 9 THEN
    SELECT concat ("Nan okerra da")ERROR;
  END IF;
  INSERT INTO bezeroa(NAN, izena, abizenak, jaiotzeData, sexua, telefonoa, pasahitza)
    VALUES (ins_Nan, ins_Izena, ins_Abizenak, ins_JaiotzeData, ins_Sexua, ins_Telefonoa, ins_Pasahitza);
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `entitateDirua` (`entitate_izena` VARCHAR(40)) RETURNS DOUBLE READS SQL DATA BEGIN
	DECLARE fin bool default 0;
    DECLARE total double default 0.00;
    DECLARE dinero double default 0.00;
    
    DECLARE c CURSOR FOR select saldoa from kontubankario k join sukurtsala s on k.id_sukurtsal=s.id_sukurtsal where s.id_entitate=(select e.id_entitate from entitatebankario e where e.izena=entitate_izena);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    OPEN c;
    FETCH c INTO dinero;
    WHILE fin =0 DO
		set total= total+dinero;
        FETCH c INTO dinero;
	END WHILE;
    CLOSE c;
    return total;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bezeroa`
--

CREATE TABLE `bezeroa` (
  `NAN` char(9) NOT NULL,
  `izena` varchar(20) DEFAULT NULL,
  `abizenak` varchar(30) DEFAULT NULL,
  `jaiotzeData` date DEFAULT NULL,
  `sexua` enum('gizona','emakumea') DEFAULT NULL,
  `telefonoa` char(9) DEFAULT NULL,
  `pasahitza` varchar(30) DEFAULT NULL,
  `egoera` enum('aktiboa','blokeatuta') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bezeroa`
--

INSERT INTO `bezeroa` (`NAN`, `izena`, `abizenak`, `jaiotzeData`, `sexua`, `telefonoa`, `pasahitza`, `egoera`) VALUES
('25097616Q', 'Iban', 'Lopez', '1988-01-01', 'gizona', '111222333', '1234', 'aktiboa'),
('51110317Q', 'Paula', 'Martinez', '1959-10-06', 'emakumea', '111222333', '1234', 'aktiboa'),
('51904221Y', 'Dolores', 'Redondo', '1933-06-05', 'emakumea', '111222333', '1234', 'aktiboa'),
('54821599H', 'Iker', 'Zuluaga', '1994-08-04', 'gizona', '111222333', '1234', 'blokeatuta'),
('55929848N', 'Laura', 'Fernandez', '2000-04-08', 'emakumea', '111222333', '1234', 'aktiboa'),
('60458544P', 'Pepe', 'Gutierrez', '1997-01-01', 'gizona', '111222333', '1234', 'aktiboa'),
('65293200H', 'Veronica', 'Lopez', '1984-11-18', 'emakumea', '111222333', '1234', 'aktiboa'),
('67606088A', 'Marta', 'Jimenez', '1992-01-01', 'emakumea', '111222333', '1234', 'aktiboa'),
('67982379Z', 'Ainhoa', 'Jimenez', '2002-08-25', 'emakumea', '111222333', '1234', 'aktiboa'),
('72270010P', 'Sergio', 'Lopez', '2002-06-06', 'gizona', '111222333', '1234', 'aktiboa'),
('74570217P', 'David', 'Fernandez', '1997-06-07', 'gizona', '111222333', '1234', 'aktiboa'),
('78950146R', 'Aingeru', 'Siranaula', '2002-10-21', 'gizona', '111222333', '1234', 'aktiboa'),
('79003399D', 'Ibai', 'Alvarez', '2000-08-16', 'gizona', '444555666', '1234', 'aktiboa'),
('79439437J', 'Hodei', 'Martinez', '2004-04-16', 'gizona', '777888999', '1234', 'aktiboa'),
('84850597D', 'Jose', 'Lambarri', '1999-06-02', 'gizona', '111222333', '1234', 'aktiboa'),
('89167975B', 'Roberto', 'Gil', '1983-01-01', 'gizona', '111222333', '1234', 'aktiboa');

--
-- Disparadores `bezeroa`
--
DELIMITER $$
CREATE TRIGGER `bezeroEzabatzean` BEFORE DELETE ON `bezeroa` FOR EACH ROW call bezeroEzabaketa(old.nan)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dirusarrera`
--

CREATE TABLE `dirusarrera` (
  `idSarrera` int(11) NOT NULL,
  `kantitatea` double DEFAULT NULL,
  `sarreraData` date DEFAULT NULL,
  `igortzaile` char(24) DEFAULT NULL,
  `kontzeptua` varchar(40) DEFAULT NULL,
  `IBANJasotzaile` char(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dirusarrera`
--

INSERT INTO `dirusarrera` (`idSarrera`, `kantitatea`, `sarreraData`, `igortzaile`, `kontzeptua`, `IBANJasotzaile`) VALUES
(1, 1230, '2023-04-04', 'ES7023450002734016972210', 'Zorionak', 'ES5123450002731634074159'),
(2, 100, '2023-04-04', 'ES7023450002734016972210', 'Oparia', 'ES1998760011555340294968'),
(3, 134.9, '2023-04-04', 'ES6323450002739061489170', 'prestamo', 'ES8798760401226868062782'),
(4, 100, '2023-04-04', 'ES9323450111313252003900', 'paga', 'ES2267890050608253351724'),
(5, 800, '2023-04-04', 'ES5854320001617546616053', 'oparia', 'ES7023450002734016972210'),
(6, 100, '2023-04-04', 'ES2967890003394765827453', 'dirua', 'ES9323450111313252003900'),
(7, 100, '2023-04-05', 'ES9323450111313252003900', 'proba', 'ES5123450002731634074159'),
(8, 100, '2023-04-05', 'ES9323450111313252003900', 'proba2', 'ES5123450002731634074159'),
(9, 1000, '2023-04-05', 'ES9323450111313252003900', 'Sorpresa', 'ES5123450002731634074159'),
(10, 300, '2023-04-05', 'ES9323450111313252003900', 'kk', 'ES5123450002731634074159');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entitatebankario`
--

CREATE TABLE `entitatebankario` (
  `id_entitate` int(11) NOT NULL,
  `izena` varchar(20) DEFAULT NULL,
  `entitateZenbaki` char(4) DEFAULT NULL,
  `url` varchar(40) DEFAULT NULL,
  `bounds` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entitatebankario`
--

INSERT INTO `entitatebankario` (`id_entitate`, `izena`, `entitateZenbaki`, `url`, `bounds`) VALUES
(1, 'BBK', '2345', 'src/res/bbk_logo.png', '218/253/128/45'),
(2, 'BBVA', '6789', 'src/res/bbva.png', '529/253/128/45'),
(3, 'Santander', '9876', 'src/res/santander.png', '218/384/128/45'),
(4, 'Kaixa', '5432', 'src/res/kaixa.png', '539/353/121/103');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hipoteka`
--

CREATE TABLE `hipoteka` (
  `idHipoteka` int(11) NOT NULL,
  `kantitatea` double DEFAULT 0,
  `ordaindutakoa` double DEFAULT 0,
  `komisioa` double DEFAULT NULL,
  `hasieraData` date DEFAULT NULL,
  `amaieraData` date DEFAULT NULL,
  `egoera` enum('eskatuta','onartuta','errefusatuta','itxita') DEFAULT 'eskatuta',
  `IBAN` char(24) DEFAULT NULL,
  `epeMuga` enum('3 urte','5 urte','10 urte','15 urte') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hipoteka`
--

INSERT INTO `hipoteka` (`idHipoteka`, `kantitatea`, `ordaindutakoa`, `komisioa`, `hasieraData`, `amaieraData`, `egoera`, `IBAN`, `epeMuga`) VALUES
(1, 100000, 0, 4, '2023-04-04', NULL, 'errefusatuta', 'ES2967890003394765827453', '15 urte'),
(2, 70000, 1000, 3.5, '2023-04-04', NULL, 'onartuta', 'ES7023450002734016972210', '5 urte'),
(3, 80000, 0, 3.8, '2023-04-04', NULL, 'eskatuta', 'ES9323450111313252003900', '10 urte'),
(4, 100000, 0, 4, '2008-04-04', '2023-05-04', 'itxita', 'ES2267890050608253351724', '15 urte'),
(5, 130000, 0, 4, '2023-05-12', NULL, 'eskatuta', 'ES3698760401240954831585', '15 urte');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kontubankario`
--

CREATE TABLE `kontubankario` (
  `IBAN` char(24) NOT NULL,
  `saldoa` double DEFAULT NULL,
  `hilekoLimitea` int(11) DEFAULT NULL,
  `sorreraData` date DEFAULT NULL,
  `egoera` enum('aktiboa','izoztuta','ixteko','itxita') DEFAULT NULL,
  `id_sukurtsal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `kontubankario`
--

INSERT INTO `kontubankario` (`IBAN`, `saldoa`, `hilekoLimitea`, `sorreraData`, `egoera`, `id_sukurtsal`) VALUES
('ES1598760011533136761561', 1000.87, 100, '2016-01-25', 'aktiboa', 8),
('ES1998760011555340294968', 3645.78, 500, '2019-03-17', 'aktiboa', 8),
('ES2054320091177386995964', 46546.45, 190, '2015-08-11', 'aktiboa', 10),
('ES2267890050608253351724', 1143.96, 100, '2008-11-01', 'ixteko', 5),
('ES2323450016240162066656', 6844.48, 1000, '2023-04-12', 'aktiboa', 3),
('ES2967890003394765827453', 900, 100, '2001-02-19', 'izoztuta', 6),
('ES3454320001655285505955', 5972.11, 1000, '2020-09-08', 'aktiboa', 12),
('ES3698760401240954831585', 1825.15, 1000, '2023-05-15', 'aktiboa', 7),
('ES5123450002731634074159', 5819.73, 600, '2019-03-20', 'aktiboa', 2),
('ES5698760401292703934204', 10367.62, 500, '2002-06-17', 'aktiboa', 7),
('ES5854320001617546616053', 11273.98, 420, '2015-04-04', 'izoztuta', 12),
('ES6323450002739061489170', 1155.09, 150, '2011-12-08', 'aktiboa', 2),
('ES6354320091153683324586', 45651.78, 10000, '2023-04-12', 'aktiboa', 10),
('ES7023450002734016972210', 3451.98, 200, '2004-01-07', 'aktiboa', 2),
('ES7323450016215447985613', 654646.64, 10000, '2021-04-12', 'aktiboa', 3),
('ES7423450016298691803331', 4564.18, 200, '2017-04-10', 'aktiboa', 3),
('ES8054320078937589601397', 1351354.11, 1000, '2016-02-09', 'izoztuta', 11),
('ES8298760153757280100739', 4165.59, 250, '2009-02-11', 'aktiboa', 9),
('ES8798760401226868062782', 2165.08, 100, '2018-10-11', 'itxita', 7),
('ES8867890003395016874189', 47845.78, 1000, '2023-04-10', 'aktiboa', 6),
('ES8954320001660087768901', 2475682.83, 3560, '2023-04-10', 'aktiboa', 12),
('ES8967890050699733431622', 8923.62, 100, '1999-06-26', 'aktiboa', 5),
('ES9323450111313252003900', 20046.09, 2000, '2003-09-15', 'aktiboa', 1),
('ES9367890050641340280882', 825.23, 170, '2014-01-07', 'aktiboa', 5),
('ES9398760153748264300420', 1564.45, 500, '2015-10-02', 'aktiboa', 9),
('ES9467895948715673183977', 8925.17, 3500, '2013-06-16', 'aktiboa', 4),
('ES9523450002793988287402', 19321.32, 100, '2005-08-12', 'aktiboa', 2),
('ES9767895948712245475232', 65465.1, 1500, '2014-11-05', 'aktiboa', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kudeatu`
--

CREATE TABLE `kudeatu` (
  `nan` char(9) NOT NULL,
  `IBAN` char(24) NOT NULL,
  `id_txartela` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `kudeatu`
--

INSERT INTO `kudeatu` (`nan`, `IBAN`, `id_txartela`) VALUES
('25097616Q', 'ES8298760153757280100739', '2617568278222036'),
('51110317Q', 'ES3698760401240954831585', '5148927072875420'),
('51110317Q', 'ES7423450016298691803331', '6468503788598927'),
('51904221Y', 'ES8054320078937589601397', '3772535106848145'),
('51904221Y', 'ES9767895948712245475232', '2480599403839222'),
('54821599H', 'ES1598760011533136761561', '3319882778963240'),
('55929848N', 'ES2267890050608253351724', '1117736392246248'),
('55929848N', 'ES8967890050699733431622', '4171310735782892'),
('65293200H', 'ES8867890003395016874189', '2409118147467715'),
('67606088A', 'ES5698760401292703934204', '9535266374567323'),
('67606088A', 'ES8798760401226868062782', '0805652957986101'),
('67982379Z', 'ES9467895948715673183977', '9333845874285391'),
('72270010P', 'ES9398760153748264300420', '2650217118025957'),
('74570217P', 'ES2054320091177386995964', '5647220382200300'),
('74570217P', 'ES2323450016240162066656', '0921628872528388'),
('74570217P', 'ES7323450016215447985613', '1383345953081161'),
('74570217P', 'ES7423450016298691803331', '7514239026816418'),
('78950146R', 'ES1998760011555340294968', '3992169351476147'),
('78950146R', 'ES6354320091153683324586', '0828400585560098'),
('78950146R', 'ES7023450002734016972210', '0070295934643037'),
('78950146R', 'ES9323450111313252003900', '8949467198213169'),
('78950146R', 'ES9523450002793988287402', '3261476784762290'),
('79003399D', 'ES9367890050641340280882', '2195906309902071'),
('79439437J', 'ES2967890003394765827453', '6051983477327273'),
('79439437J', 'ES6323450002739061489170', '5209365874466295'),
('84850597D', 'ES8954320001660087768901', '2573710633083995'),
('89167975B', 'ES3454320001655285505955', '5527263342821632'),
('89167975B', 'ES5123450002731634074159', '4015538127623375'),
('89167975B', 'ES5854320001617546616053', '0920374254122547');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `langile`
--

CREATE TABLE `langile` (
  `nan` char(9) NOT NULL,
  `izena` varchar(20) DEFAULT NULL,
  `abizenak` varchar(30) DEFAULT NULL,
  `jaiotzeData` date DEFAULT NULL,
  `sexua` enum('gizona','emakumea') DEFAULT NULL,
  `telefonoa` char(9) DEFAULT NULL,
  `pasahitza` varchar(20) DEFAULT NULL,
  `lanpostua` enum('god','zuzendaria','gerentea') DEFAULT NULL,
  `id_sukurtsal` int(11) DEFAULT NULL,
  `egoera` enum('aktiboa','blokeatuta') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `langile`
--

INSERT INTO `langile` (`nan`, `izena`, `abizenak`, `jaiotzeData`, `sexua`, `telefonoa`, `pasahitza`, `lanpostua`, `id_sukurtsal`, `egoera`) VALUES
('12345678Z', 'God', 'God', '2000-10-21', 'gizona', '000000000', '1234', 'god', 1, 'aktiboa'),
('47757325X', 'Egoitz', 'Perez', '2000-05-05', 'gizona', '111222333', '1234', 'gerentea', 5, 'aktiboa'),
('78950146R', 'Aingeru', 'Siranaula', '2000-10-21', 'gizona', '111222333', '1234', 'zuzendaria', 4, 'aktiboa'),
('79003399D', 'Ibai', 'Alvarez', '2000-10-21', 'gizona', '111222333', '1234', 'zuzendaria', 1, 'aktiboa'),
('79439437J', 'Hodei', 'Martinez', '2004-04-16', 'gizona', '111222333', '1234', 'zuzendaria', 9, 'aktiboa'),
('90138299B', 'Jon', 'Garcia', '1983-09-17', 'gizona', '111222333', '1234', 'gerentea', 9, 'aktiboa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sukurtsala`
--

CREATE TABLE `sukurtsala` (
  `kodSukurtsala` char(4) DEFAULT NULL,
  `kokalekua` varchar(80) DEFAULT NULL,
  `id_sukurtsal` int(11) NOT NULL,
  `id_entitate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sukurtsala`
--

INSERT INTO `sukurtsala` (`kodSukurtsala`, `kokalekua`, `id_sukurtsal`, `id_entitate`) VALUES
('0111', 'Santutxu, Santutxu Kalea, 27', 1, 1),
('0002', 'Urkixo Zumarkalea, 56', 2, 1),
('0016', 'Alameda de Recalde, 26', 3, 1),
('5948', 'Areiltza Doktorearen Zumarkalea', 4, 2),
('0050', 'C. de Bertendona, 4', 5, 2),
('0003', 'Alameda de Recalde, 35', 6, 2),
('0401', 'Indautxu Plaza, 1', 7, 3),
('0011', 'Iparraguirre Kalea, 16', 8, 3),
('0153', 'Autonomia Kalea, 18', 9, 3),
('0091', 'Gran Vía de Don Diego López de Aro', 10, 4),
('0078', 'Autonomia Kalea, 54', 11, 4),
('0001', 'Alameda de Recalde, 44', 12, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transferentzia`
--

CREATE TABLE `transferentzia` (
  `idTransferentzia` int(11) NOT NULL,
  `kantitatea` double DEFAULT NULL,
  `TransferentziaData` date DEFAULT NULL,
  `jasotzailea` char(24) DEFAULT NULL,
  `kontzeptua` varchar(50) DEFAULT NULL,
  `komisioa` double DEFAULT NULL,
  `IBANIgortzaile` char(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transferentzia`
--

INSERT INTO `transferentzia` (`idTransferentzia`, `kantitatea`, `TransferentziaData`, `jasotzailea`, `kontzeptua`, `komisioa`, `IBANIgortzaile`) VALUES
(1, 1230, '2023-04-04', 'ES5123450002731634074159', 'Zorionak', 1.5, 'ES7023450002734016972210'),
(2, 100, '2023-04-04', 'ES1998760011555340294968', 'Oparia', 1.5, 'ES7023450002734016972210'),
(3, 134.9, '2023-04-04', 'ES8798760401226868062782', 'prestamo', 1.5, 'ES6323450002739061489170'),
(4, 100, '2023-04-04', 'ES2267890050608253351724', 'paga', 1.5, 'ES9323450111313252003900'),
(5, 800, '2023-04-04', 'ES7023450002734016972210', 'oparia', 1.5, 'ES5854320001617546616053'),
(6, 100, '2023-04-04', 'ES9323450111313252003900', 'dirua', 1.5, 'ES2967890003394765827453'),
(7, 100, '2023-04-05', 'ES5123450002731634074159', 'proba', 1.5, 'ES9323450111313252003900'),
(8, 100, '2023-04-05', 'ES5123450002731634074159', 'proba2', 1.5, 'ES9323450111313252003900'),
(9, 1000, '2023-04-05', 'ES5123450002731634074159', 'Sorpresa', 1.5, 'ES9323450111313252003900'),
(10, 300, '2023-04-05', 'ES5123450002731634074159', 'kk', 1.5, 'ES9323450111313252003900');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `txartela`
--

CREATE TABLE `txartela` (
  `id_txartela` char(16) NOT NULL,
  `segurtasunKodea` char(4) DEFAULT NULL,
  `mota` enum('kredito','debito') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `txartela`
--

INSERT INTO `txartela` (`id_txartela`, `segurtasunKodea`, `mota`) VALUES
('0070295934643037', '5678', 'debito'),
('0805652957986101', '7346', 'kredito'),
('0828400585560098', '5678', 'kredito'),
('0920374254122547', '4676', 'kredito'),
('0921628872528388', '3427', 'kredito'),
('1117736392246248', '4738', 'kredito'),
('1383345953081161', '7893', 'debito'),
('2195906309902071', '2034', 'debito'),
('2409118147467715', '8765', 'debito'),
('2480599403839222', '4357', 'kredito'),
('2573710633083995', '8765', 'kredito'),
('2617568278222036', '3499', 'debito'),
('2650217118025957', '8814', 'debito'),
('3261476784762290', '5678', 'debito'),
('3319882778963240', '6543', 'debito'),
('3772535106848145', '3694', 'kredito'),
('3992169351476147', '5678', 'kredito'),
('4015538127623375', '5732', 'debito'),
('4171310735782892', '3820', 'debito'),
('5148927072875420', '3194', 'debito'),
('5209365874466295', '2936', 'kredito'),
('5527263342821632', '8465', 'debito'),
('5647220382200300', '8524', 'kredito'),
('6051983477327273', '9372', 'kredito'),
('6468503788598927', '4319', 'kredito'),
('7514239026816418', '1726', 'kredito'),
('8949467198213169', '5678', 'kredito'),
('9333845874285391', '7891', 'debito'),
('9535266374567323', '8334', 'kredito');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bezeroa`
--
ALTER TABLE `bezeroa`
  ADD PRIMARY KEY (`NAN`);

--
-- Indices de la tabla `dirusarrera`
--
ALTER TABLE `dirusarrera`
  ADD PRIMARY KEY (`idSarrera`),
  ADD KEY `Fk_Sarrera_KontuBankario` (`IBANJasotzaile`);

--
-- Indices de la tabla `entitatebankario`
--
ALTER TABLE `entitatebankario`
  ADD PRIMARY KEY (`id_entitate`),
  ADD KEY `idx_izena_entitateZenbaki` (`izena`,`entitateZenbaki`);

--
-- Indices de la tabla `hipoteka`
--
ALTER TABLE `hipoteka`
  ADD PRIMARY KEY (`idHipoteka`),
  ADD KEY `idx_hipoteka_ibanea_egoera` (`IBAN`,`egoera`);

--
-- Indices de la tabla `kontubankario`
--
ALTER TABLE `kontubankario`
  ADD PRIMARY KEY (`IBAN`),
  ADD KEY `Fk_KontuBankarioa_Sukurtsala` (`id_sukurtsal`);

--
-- Indices de la tabla `kudeatu`
--
ALTER TABLE `kudeatu`
  ADD PRIMARY KEY (`nan`,`IBAN`,`id_txartela`),
  ADD KEY `Fk_kudeatu_kontubankarioa` (`IBAN`),
  ADD KEY `Fk_kudeatu_txartela` (`id_txartela`);

--
-- Indices de la tabla `langile`
--
ALTER TABLE `langile`
  ADD PRIMARY KEY (`nan`),
  ADD KEY `Fk_Langile_Sukurtsal` (`id_sukurtsal`),
  ADD KEY `idx_langile_lanpostu` (`lanpostua`);

--
-- Indices de la tabla `sukurtsala`
--
ALTER TABLE `sukurtsala`
  ADD PRIMARY KEY (`id_sukurtsal`),
  ADD KEY `Fk_Sukurtsal_Entitate` (`id_entitate`),
  ADD KEY `idx_sukurtsala_kodsuskurtsal` (`kodSukurtsala`);

--
-- Indices de la tabla `transferentzia`
--
ALTER TABLE `transferentzia`
  ADD PRIMARY KEY (`idTransferentzia`),
  ADD KEY `FK_Transferentzia_KontuBankarioa` (`IBANIgortzaile`),
  ADD KEY `idx_transferentzia` (`kantitatea`,`jasotzailea`,`IBANIgortzaile`);

--
-- Indices de la tabla `txartela`
--
ALTER TABLE `txartela`
  ADD PRIMARY KEY (`id_txartela`),
  ADD KEY `idx_txartela_mota` (`mota`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `dirusarrera`
--
ALTER TABLE `dirusarrera`
  MODIFY `idSarrera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `entitatebankario`
--
ALTER TABLE `entitatebankario`
  MODIFY `id_entitate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `hipoteka`
--
ALTER TABLE `hipoteka`
  MODIFY `idHipoteka` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `transferentzia`
--
ALTER TABLE `transferentzia`
  MODIFY `idTransferentzia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dirusarrera`
--
ALTER TABLE `dirusarrera`
  ADD CONSTRAINT `Fk_Sarrera_KontuBankario` FOREIGN KEY (`IBANJasotzaile`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE;

--
-- Filtros para la tabla `hipoteka`
--
ALTER TABLE `hipoteka`
  ADD CONSTRAINT `FK_Hipoteka_KontuBankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE;

--
-- Filtros para la tabla `kontubankario`
--
ALTER TABLE `kontubankario`
  ADD CONSTRAINT `Fk_KontuBankarioa_Sukurtsala` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`) ON DELETE CASCADE;

--
-- Filtros para la tabla `kudeatu`
--
ALTER TABLE `kudeatu`
  ADD CONSTRAINT `Fk_kudeatu_bezero` FOREIGN KEY (`nan`) REFERENCES `bezeroa` (`NAN`) ON DELETE CASCADE,
  ADD CONSTRAINT `Fk_kudeatu_kontubankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE,
  ADD CONSTRAINT `Fk_kudeatu_txartela` FOREIGN KEY (`id_txartela`) REFERENCES `txartela` (`id_txartela`) ON DELETE CASCADE;

--
-- Filtros para la tabla `langile`
--
ALTER TABLE `langile`
  ADD CONSTRAINT `Fk_Langile_Sukurtsal` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `sukurtsala`
--
ALTER TABLE `sukurtsala`
  ADD CONSTRAINT `Fk_Sukurtsal_Entitate` FOREIGN KEY (`id_entitate`) REFERENCES `entitatebankario` (`id_entitate`) ON DELETE CASCADE;

--
-- Filtros para la tabla `transferentzia`
--
ALTER TABLE `transferentzia`
  ADD CONSTRAINT `FK_Transferentzia_KontuBankarioa` FOREIGN KEY (`IBANIgortzaile`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE;

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `komisioSaldo` ON SCHEDULE AT '2023-11-15 10:35:42' ON COMPLETION PRESERVE ENABLE DO update kontubankario
    set saldoa = round(saldoa*1.002,2)$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
