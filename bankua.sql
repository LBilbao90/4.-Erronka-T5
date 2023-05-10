-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: bankua
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bezeroa`
--

DROP TABLE IF EXISTS `bezeroa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bezeroa` (
  `NAN` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `izena` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `abizenak` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `jaiotzeData` date DEFAULT NULL,
  `sexua` enum('gizona','emakumea') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefonoa` char(9) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pasahitza` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `egoera` enum('aktiboa','blokeatuta') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`NAN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bezeroa`
--

LOCK TABLES `bezeroa` WRITE;
/*!40000 ALTER TABLE `bezeroa` DISABLE KEYS */;
INSERT INTO `bezeroa` VALUES ('54821599H','Iker','Zuluaga','1994-08-04','gizona','111222333','1234','blokeatuta'),('55929848N','Laura','Fernandez','2000-04-08','emakumea','111222333','1234','aktiboa'),('67606088A','Marta','Jimenez','1992-01-01','emakumea','111222333','1234','aktiboa'),('67982379Z','Ainhoa','Jimenez','2002-08-25','emakumea','111222333','1234','aktiboa'),('78950146R','Aingeru','Siranaula','2002-10-21','gizona','111222333','1234','aktiboa'),('79003399D','Ibai','Alvarez','2000-08-16','gizona','444555666','1234','aktiboa'),('79439437J','Hodei','Martinez','2004-04-16','gizona','777888999','1234','aktiboa'),('89167975B','Roberto','Gil','1983-01-01','gizona','111222333','1234','aktiboa');
/*!40000 ALTER TABLE `bezeroa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dirusarrera`
--

DROP TABLE IF EXISTS `dirusarrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dirusarrera` (
  `idSarrera` int NOT NULL AUTO_INCREMENT,
  `kantitatea` double DEFAULT NULL,
  `sarreraData` date DEFAULT NULL,
  `igortzaile` char(24) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kontzeptua` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `IBANJasotzaile` char(24) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`idSarrera`),
  KEY `Fk_Sarrera_KontuBankario` (`IBANJasotzaile`),
  CONSTRAINT `Fk_Sarrera_KontuBankario` FOREIGN KEY (`IBANJasotzaile`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dirusarrera`
--

LOCK TABLES `dirusarrera` WRITE;
/*!40000 ALTER TABLE `dirusarrera` DISABLE KEYS */;
INSERT INTO `dirusarrera` VALUES (1,1230,'2023-04-04','ES7023450002734016972210','Zorionak','ES5123450002731634074159'),(2,100,'2023-04-04','ES7023450002734016972210','Oparia','ES1998760011555340294968'),(3,134.9,'2023-04-04','ES6323450002739061489170','prestamo','ES8798760401226868062782'),(4,100,'2023-04-04','ES9323450111313252003900','paga','ES2267890050608253351724'),(5,800,'2023-04-04','ES5854320001617546616053','oparia','ES7023450002734016972210'),(6,100,'2023-04-04','ES2967890003394765827453','dirua','ES9323450111313252003900'),(7,100,'2023-04-05','ES9323450111313252003900','proba','ES5123450002731634074159'),(8,100,'2023-04-05','ES9323450111313252003900','proba2','ES5123450002731634074159'),(9,1000,'2023-04-05','ES9323450111313252003900','Sorpresa','ES5123450002731634074159'),(10,300,'2023-04-05','ES9323450111313252003900','kk','ES5123450002731634074159');
/*!40000 ALTER TABLE `dirusarrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entitatebankario`
--

DROP TABLE IF EXISTS `entitatebankario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entitatebankario` (
  `id_entitate` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `entitateZenbaki` char(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bounds` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_entitate`),
  KEY `idx_izena_entitateZenbaki` (`izena`,`entitateZenbaki`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entitatebankario`
--

LOCK TABLES `entitatebankario` WRITE;
/*!40000 ALTER TABLE `entitatebankario` DISABLE KEYS */;
INSERT INTO `entitatebankario` VALUES (1,'BBK','2345','src/res/bbk_logo.png','218/253/128/45'),(2,'BBVA','6789','src/res/bbva.png','529/253/128/45'),(3,'Santander','9876','src/res/santander.png','218/384/128/45'),(4,'Kaixa','5432','src/res/kaixa.png','539/353/121/103');
/*!40000 ALTER TABLE `entitatebankario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hipoteka`
--

DROP TABLE IF EXISTS `hipoteka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hipoteka` (
  `idHipoteka` int NOT NULL AUTO_INCREMENT,
  `kantitatea` double DEFAULT '0',
  `ordaindutakoa` double DEFAULT '0',
  `komisioa` double DEFAULT NULL,
  `hasieraData` date DEFAULT NULL,
  `amaieraData` date DEFAULT NULL,
  `egoera` enum('eskatuta','onartuta','errefusatuta','itxita') COLLATE utf8mb4_general_ci DEFAULT 'eskatuta',
  `IBAN` char(24) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `epeMuga` enum('3 urte','5 urte','10 urte','15 urte') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`idHipoteka`),
  KEY `idx_hipoteka_ibanea_egoera` (`IBAN`,`egoera`),
  CONSTRAINT `FK_Hipoteka_KontuBankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hipoteka`
--

LOCK TABLES `hipoteka` WRITE;
/*!40000 ALTER TABLE `hipoteka` DISABLE KEYS */;
INSERT INTO `hipoteka` VALUES (1,100000,0,4,'2023-04-04',NULL,'errefusatuta','ES2967890003394765827453','15 urte'),(2,70000,1000,3.5,'2023-04-04',NULL,'onartuta','ES7023450002734016972210','5 urte'),(3,80000,0,3.8,'2023-04-04',NULL,'eskatuta','ES9323450111313252003900','10 urte'),(4,100000,0,4,'2008-04-04','2023-05-04','itxita','ES2267890050608253351724','15 urte');
/*!40000 ALTER TABLE `hipoteka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kontubankario`
--

DROP TABLE IF EXISTS `kontubankario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kontubankario` (
  `IBAN` char(24) COLLATE utf8mb4_general_ci NOT NULL,
  `saldoa` double DEFAULT NULL,
  `hilekoLimitea` int DEFAULT NULL,
  `sorreraData` date DEFAULT NULL,
  `egoera` enum('aktiboa','izoztuta','ixteko','itxita') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_sukurtsal` int DEFAULT NULL,
  PRIMARY KEY (`IBAN`),
  KEY `Fk_KontuBankarioa_Sukurtsala` (`id_sukurtsal`),
  CONSTRAINT `Fk_KontuBankarioa_Sukurtsala` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kontubankario`
--

LOCK TABLES `kontubankario` WRITE;
/*!40000 ALTER TABLE `kontubankario` DISABLE KEYS */;
INSERT INTO `kontubankario` VALUES ('ES1598760011533136761561',1000.87,100,'2023-04-07','aktiboa',8),('ES1998760011555340294968',3645.78,500,'2022-03-13','aktiboa',8),('ES2267890050608253351724',1143.96,100,'2008-11-01','ixteko',5),('ES2967890003394765827453',900,100,'2001-02-19','izoztuta',6),('ES3454320001655285505955',5972.11,1000,'2020-09-08','aktiboa',12),('ES5123450002731634074159',5819.73,600,'2019-03-20','aktiboa',2),('ES5698760401292703934204',10367.62,500,'2002-06-17','aktiboa',7),('ES5854320001617546616053',11273.98,420,'2015-04-04','izoztuta',12),('ES6323450002739061489170',1155.09,150,'2011-12-08','aktiboa',2),('ES7023450002734016972210',3451.98,200,'2004-01-07','aktiboa',2),('ES8798760401226868062782',2165.08,100,'2018-10-11','itxita',7),('ES8967890050699733431622',8923.62,100,'1999-06-26','aktiboa',5),('ES9323450111313252003900',20046.09,2000,'2003-09-15','aktiboa',1),('ES9367890050641340280882',825.23,170,'2014-01-07','aktiboa',5),('ES9523450002793988287402',19321.32,100,'2005-08-12','aktiboa',2);
/*!40000 ALTER TABLE `kontubankario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kudeatu`
--

DROP TABLE IF EXISTS `kudeatu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kudeatu` (
  `nan` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `IBAN` char(24) COLLATE utf8mb4_general_ci NOT NULL,
  `id_txartela` char(16) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`nan`,`IBAN`,`id_txartela`),
  KEY `Fk_kudeatu_kontubankarioa` (`IBAN`),
  KEY `Fk_kudeatu_txartela` (`id_txartela`),
  CONSTRAINT `Fk_kudeatu_bezero` FOREIGN KEY (`nan`) REFERENCES `bezeroa` (`NAN`) ON DELETE CASCADE,
  CONSTRAINT `Fk_kudeatu_kontubankarioa` FOREIGN KEY (`IBAN`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE,
  CONSTRAINT `Fk_kudeatu_txartela` FOREIGN KEY (`id_txartela`) REFERENCES `txartela` (`id_txartela`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kudeatu`
--

LOCK TABLES `kudeatu` WRITE;
/*!40000 ALTER TABLE `kudeatu` DISABLE KEYS */;
INSERT INTO `kudeatu` VALUES ('78950146R','ES7023450002734016972210','0070295934643037'),('67606088A','ES8798760401226868062782','0805652957986101'),('89167975B','ES5854320001617546616053','0920374254122547'),('55929848N','ES2267890050608253351724','1117736392246248'),('79003399D','ES9367890050641340280882','2195906309902071'),('78950146R','ES9523450002793988287402','3261476784762290'),('54821599H','ES1598760011533136761561','3319882778963240'),('78950146R','ES1998760011555340294968','3992169351476147'),('89167975B','ES5123450002731634074159','4015538127623375'),('55929848N','ES8967890050699733431622','4171310735782892'),('79439437J','ES6323450002739061489170','5209365874466295'),('89167975B','ES3454320001655285505955','5527263342821632'),('79439437J','ES2967890003394765827453','6051983477327273'),('78950146R','ES9323450111313252003900','8949467198213169'),('67606088A','ES5698760401292703934204','9535266374567323');
/*!40000 ALTER TABLE `kudeatu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `langile`
--

DROP TABLE IF EXISTS `langile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langile` (
  `id_langile` int NOT NULL AUTO_INCREMENT,
  `nan` char(9) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `izena` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `abizenak` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `jaiotzeData` date DEFAULT NULL,
  `sexua` enum('gizona','emakumea') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefonoa` char(9) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pasahitza` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lanpostua` enum('god','zuzendaria','gerentea') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_sukurtsal` int DEFAULT NULL,
  `egoera` enum('aktiboa','blokeatuta') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_langile`),
  KEY `Fk_Langile_Sukurtsal` (`id_sukurtsal`),
  KEY `idx_langile_lanpostu` (`lanpostua`),
  CONSTRAINT `Fk_Langile_Sukurtsal` FOREIGN KEY (`id_sukurtsal`) REFERENCES `sukurtsala` (`id_sukurtsal`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langile`
--

LOCK TABLES `langile` WRITE;
/*!40000 ALTER TABLE `langile` DISABLE KEYS */;
INSERT INTO `langile` VALUES (1,'79003399D','Ibai','Alvarez','2000-10-21','gizona','111222333','1234','zuzendaria',1,'aktiboa'),(2,'12345678Z','GOD','TU PADRE','2000-10-21','gizona','000000000','1234','god',1,'aktiboa'),(3,'78950146R','Aingeru','Siranaula','2000-10-21','gizona','111222333','1234','zuzendaria',4,'aktiboa'),(4,'79439437J','Hodei','Martinez','2004-04-16','gizona','111222333','1234','zuzendaria',9,'aktiboa'),(5,'90138299B','Jon','Garcia','1983-09-17','gizona','111222333','1234','gerentea',9,'aktiboa');
/*!40000 ALTER TABLE `langile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sukurtsala`
--

DROP TABLE IF EXISTS `sukurtsala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sukurtsala` (
  `kodSukurtsala` char(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kokalekua` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_sukurtsal` int NOT NULL,
  `id_entitate` int DEFAULT NULL,
  PRIMARY KEY (`id_sukurtsal`),
  KEY `Fk_Sukurtsal_Entitate` (`id_entitate`),
  KEY `idx_sukurtsala_kodsuskurtsal` (`kodSukurtsala`),
  CONSTRAINT `Fk_Sukurtsal_Entitate` FOREIGN KEY (`id_entitate`) REFERENCES `entitatebankario` (`id_entitate`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sukurtsala`
--

LOCK TABLES `sukurtsala` WRITE;
/*!40000 ALTER TABLE `sukurtsala` DISABLE KEYS */;
INSERT INTO `sukurtsala` VALUES ('0111','Santutxu, Santutxu Kalea, 27',1,1),('0002','Urkixo Zumarkalea, 56',2,1),('0016','Alameda de Recalde, 26',3,1),('5948','Areiltza Doktorearen Zumarkale',4,2),('0050','C. de Bertendona, 4',5,2),('0003','Alameda de Recalde, 35',6,2),('0401','Indautxu Plaza, 1',7,3),('0011','Iparraguirre Kalea, 16',8,3),('0153','Autonomia Kalea, 18',9,3),('0091','Gran Vía de Don Diego López de Aro',10,4),('0078','Autonomia Kalea, 54',11,4),('0001','Alameda de Recalde, 44',12,4);
/*!40000 ALTER TABLE `sukurtsala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferentzia`
--

DROP TABLE IF EXISTS `transferentzia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferentzia` (
  `idTransferentzia` int NOT NULL AUTO_INCREMENT,
  `kantitatea` double DEFAULT NULL,
  `TransferentziaData` date DEFAULT NULL,
  `jasotzailea` char(24) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kontzeptua` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `komisioa` double DEFAULT NULL,
  `IBANIgortzaile` char(24) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`idTransferentzia`),
  KEY `FK_Transferentzia_KontuBankarioa` (`IBANIgortzaile`),
  KEY `idx_transferentzia` (`kantitatea`,`jasotzailea`,`IBANIgortzaile`),
  CONSTRAINT `FK_Transferentzia_KontuBankarioa` FOREIGN KEY (`IBANIgortzaile`) REFERENCES `kontubankario` (`IBAN`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferentzia`
--

LOCK TABLES `transferentzia` WRITE;
/*!40000 ALTER TABLE `transferentzia` DISABLE KEYS */;
INSERT INTO `transferentzia` VALUES (1,1230,'2023-04-04','ES5123450002731634074159','Zorionak',1.5,'ES7023450002734016972210'),(2,100,'2023-04-04','ES1998760011555340294968','Oparia',1.5,'ES7023450002734016972210'),(3,134.9,'2023-04-04','ES8798760401226868062782','prestamo',1.5,'ES6323450002739061489170'),(4,100,'2023-04-04','ES2267890050608253351724','paga',1.5,'ES9323450111313252003900'),(5,800,'2023-04-04','ES7023450002734016972210','oparia',1.5,'ES5854320001617546616053'),(6,100,'2023-04-04','ES9323450111313252003900','dirua',1.5,'ES2967890003394765827453'),(7,100,'2023-04-05','ES5123450002731634074159','proba',1.5,'ES9323450111313252003900'),(8,100,'2023-04-05','ES5123450002731634074159','proba2',1.5,'ES9323450111313252003900'),(9,1000,'2023-04-05','ES5123450002731634074159','Sorpresa',1.5,'ES9323450111313252003900'),(10,300,'2023-04-05','ES5123450002731634074159','kk',1.5,'ES9323450111313252003900');
/*!40000 ALTER TABLE `transferentzia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `txartela`
--

DROP TABLE IF EXISTS `txartela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `txartela` (
  `id_txartela` char(16) COLLATE utf8mb4_general_ci NOT NULL,
  `segurtasunKodea` char(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mota` enum('kredito','debito') COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_txartela`),
  KEY `idx_txartela_mota` (`mota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `txartela`
--

LOCK TABLES `txartela` WRITE;
/*!40000 ALTER TABLE `txartela` DISABLE KEYS */;
INSERT INTO `txartela` VALUES ('0070295934643037','5678','debito'),('0805652957986101','7346','kredito'),('0920374254122547','4676','kredito'),('1117736392246248','4738','kredito'),('2195906309902071','2034','debito'),('3261476784762290','5678','debito'),('3319882778963240','6543','debito'),('3992169351476147','5678','kredito'),('4015538127623375','5732','debito'),('4171310735782892','3820','debito'),('5209365874466295','2936','kredito'),('5527263342821632','8465','debito'),('6051983477327273','9372','kredito'),('8949467198213169','5678','kredito'),('9535266374567323','8334','kredito');
/*!40000 ALTER TABLE `txartela` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 13:58:03
