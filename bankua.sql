-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2023 a las 10:18:11
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

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
  `pasahitza` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bezeroa`
--

INSERT INTO `bezeroa` (`NAN`, `izena`, `abizenak`, `jaiotzeData`, `sexua`, `telefonoa`, `pasahitza`) VALUES
('12345678A', 'Aingeru', 'Siranaula', '2002-10-21', 'gizona', '111222333', '12345678'),
('12345678B', 'Ibai', 'Alvarez', '2000-08-16', 'gizona', '444555666', '1234'),
('12345678C', 'Hodei', 'Martinez', '2004-04-16', 'gizona', '777888999', '12345678');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entitatebankario`
--

CREATE TABLE `entitatebankario` (
  `id_entitate` int(11) NOT NULL,
  `izena` varchar(20) DEFAULT NULL,
  `entitateZenbaki` char(8) DEFAULT NULL,
  `url` varchar(40) DEFAULT NULL,
  `bounds` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entitatebankario`
--

INSERT INTO `entitatebankario` (`id_entitate`, `izena`, `entitateZenbaki`, `url`, `bounds`) VALUES
(1, 'BBK', 'ES972345', 'src/res/bbk_logo.png', '218/253/128/45'),
(2, 'BBVA', 'ES346789', 'src/res/bbva.png', '529/253/128/45'),
(3, 'Santander', 'ES259876', 'src/res/santander.png', '218/384/128/45'),
(4, 'Caixa', 'ES065432', 'src/res/kaixa.png', '539/353/121/103');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hipoteka`
--

CREATE TABLE `hipoteka` (
  `idHipoteka` int(11) NOT NULL,
  `kantitatea` double DEFAULT NULL,
  `ordaindutakoa` double DEFAULT NULL,
  `komisioa` double DEFAULT NULL,
  `hasieraData` date DEFAULT NULL,
  `amaieraData` date DEFAULT NULL,
  `egoera` enum('eskatuta','onartuta','errefusatua','itxita') DEFAULT NULL,
  `IBAN` char(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kudeatu`
--

CREATE TABLE `kudeatu` (
  `nan` char(9) NOT NULL,
  `IBAN` char(24) NOT NULL,
  `id_txartela` char(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `langile`
--

CREATE TABLE `langile` (
  `id_langile` int(11) NOT NULL,
  `nan` char(9) DEFAULT NULL,
  `izena` varchar(20) DEFAULT NULL,
  `abizenak` varchar(30) DEFAULT NULL,
  `jaiotzeData` date DEFAULT NULL,
  `sexua` enum('gizon','emakume') DEFAULT NULL,
  `telefonoa` char(9) DEFAULT NULL,
  `pasahitza` varchar(20) DEFAULT NULL,
  `lanpostua` enum('god','zuzendaria','gerentea') DEFAULT NULL,
  `id_sukurtsal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `langile`
--

INSERT INTO `langile` (`id_langile`, `nan`, `izena`, `abizenak`, `jaiotzeData`, `sexua`, `telefonoa`, `pasahitza`, `lanpostua`, `id_sukurtsal`) VALUES
(1, '12345678B', 'Ibai', 'Alvarez', '2000-10-21', 'gizon', '111222333', '1234', 'zuzendaria', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sukurtsala`
--

CREATE TABLE `sukurtsala` (
  `kodSukurtsala` char(4) DEFAULT NULL,
  `kokalekua` varchar(30) DEFAULT NULL,
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
('5948', 'Areiltza Doktorearen Zumarkale', 4, 2),
('0050', 'C. de Bertendona, 4', 5, 2),
('0003', 'Alameda de Recalde, 35', 6, 2),
('0401', 'Indautxu Plaza, 1', 7, 3),
('0011', 'Iparraguirre Kalea, 16', 8, 3),
('0153', 'Autonomia Kalea, 18', 9, 3),
('0091', 'Gran Vía de Don Diego López de', 10, 4),
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
  ADD PRIMARY KEY (`id_langile`),
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
  MODIFY `idSarrera` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entitatebankario`
--
ALTER TABLE `entitatebankario`
  MODIFY `id_entitate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `hipoteka`
--
ALTER TABLE `hipoteka`
  MODIFY `idHipoteka` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `langile`
--
ALTER TABLE `langile`
  MODIFY `id_langile` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `transferentzia`
--
ALTER TABLE `transferentzia`
  MODIFY `idTransferentzia` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dirusarrera`
--
ALTER TABLE `dirusarrera`
  ADD CONSTRAINT `Fk_Sarrera_KontuBankario` FOREIGN KEY (`IBANJasotzaile`) REFERENCES `kontubankario` (`IBAN`);

--
-- Filtros para la tabla `hipoteka`
--
ALTER TABLE `hipoteka`
  ADD CONSTRAINT `FK_Hipoteka_KontuBankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`);

--
-- Filtros para la tabla `kontubankario`
--
ALTER TABLE `kontubankario`
  ADD CONSTRAINT `Fk_KontuBankarioa_Sukurtsala` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`);

--
-- Filtros para la tabla `kudeatu`
--
ALTER TABLE `kudeatu`
  ADD CONSTRAINT `Fk_kudeatu_bezero` FOREIGN KEY (`nan`) REFERENCES `bezeroa` (`NAN`),
  ADD CONSTRAINT `Fk_kudeatu_kontubankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`),
  ADD CONSTRAINT `Fk_kudeatu_txartela` FOREIGN KEY (`id_txartela`) REFERENCES `txartela` (`id_txartela`);

--
-- Filtros para la tabla `langile`
--
ALTER TABLE `langile`
  ADD CONSTRAINT `Fk_Langile_Sukurtsal` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`);

--
-- Filtros para la tabla `sukurtsala`
--
ALTER TABLE `sukurtsala`
  ADD CONSTRAINT `Fk_Sukurtsal_Entitate` FOREIGN KEY (`id_entitate`) REFERENCES `entitatebankario` (`id_entitate`);

--
-- Filtros para la tabla `transferentzia`
--
ALTER TABLE `transferentzia`
  ADD CONSTRAINT `FK_Transferentzia_KontuBankarioa` FOREIGN KEY (`IBANIgortzaile`) REFERENCES `kontubankario` (`IBAN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
