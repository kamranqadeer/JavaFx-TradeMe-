-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: trademe
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `trademe`
--

/*!40000 DROP DATABASE IF EXISTS `trademe`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `trademe` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `trademe`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'KAMRANQADEER','Installer','kamran.qadeer.26@gmail.com','0344-4200515','kamran9808kk','Deactive','Active'),(2,'NASIR','Admin','nasir@gmail.com','0344-4200515','kamrankk','Active','Active'),(3,'KAMRANKK','User','kk@gmail.com','0212-1221212','kamrankk','Deactive','Active');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `all_month`
--

DROP TABLE IF EXISTS `all_month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `all_month` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=428 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `all_month`
--

LOCK TABLES `all_month` WRITE;
/*!40000 ALTER TABLE `all_month` DISABLE KEYS */;
INSERT INTO `all_month` VALUES (411,'December_2020','Wipper Floora','.8  Per Dozen','925.2  Rs','.4  Per Dozen','512.5  Rs'),(413,'December_2020','Chamical Tzab','10.0  Per Pice','7000.0  Rs','0','0'),(414,'December_2020','Jharoo LambaTela','.0  ','.0  Rs','0','0'),(415,'December_2020','Chamical ShampoBase','.0  ','.0  Rs','0','0'),(416,'December_2020','Chamical ColourRed','.0  ','.0  Rs','0','0'),(417,'December_2020','Lable Lizol','.0  ','.0  Rs','0','0'),(418,'December_2020','Jharoo Small','20.0  Per Dozen','7200.0  Rs','0','0'),(419,'December_2020','Botals Sweep','.0  ','.0  Rs','0','0'),(420,'December_2020','Chamical RungCart','.0  ','.0  Rs','0','0'),(421,'December_2020','Jharoo Chota Tela','.0  ','.0  Rs','0','0'),(422,'December_2020','Chamical Dp200','.0  ','.0  Rs','0','0'),(423,'December_2020','Chamical Broza','.0  ','.0  Rs','0','0'),(424,'December_2020','Chamical ExtraPower','.0  ','.0  Rs','0','0'),(425,'December_2020','Chamical Castic','.0  ','.0  Rs','0','0'),(426,'December_2020','Jharoo lambatela200gm','.0  ','.0  Rs','0','0'),(427,'January_2021','Wipper Malta','0','0','.2  Per Dozen','225.0  Rs'),(412,'December_2020','Jharoo Phool','1.8  Per Dozen','1207.7  Rs','0','0'),(410,'December_2020','Wipper Malta','.8  Per Dozen','1075.2  Rs','0','0');
/*!40000 ALTER TABLE `all_month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`C1`),
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'2020-10-19 08:42:01','Jharoo LambaTela','1539.96','Per Dozen','1719.60','Class A','20'),(4,'2020-10-19 08:42:23','Wipper Malta','107.52','Per Pice','112.5','Calss B','10'),(5,'2020-10-19 10:08:13','Jharoo Phool','57.51','Per Pice','67.5','Class A','10'),(6,'2020-10-29 05:26:58','Chamical ShampoBase','624.00','Per Kg','630','Calss B','5'),(7,'2020-10-29 05:51:23','Chamical Tzab','700','Per Can','800.00','Calss B','20'),(8,'2020-10-30 13:27:15','Chamical ColourRed','1100.00','Per Kg','1200','Class C','5'),(9,'2020-11-01 08:26:54','Lable Lizol','2.06','Per Pice','3','Class D','10'),(10,'2020-11-01 08:27:34','Jharoo Small','360.00','Per Dozen','384.0','Class C','10'),(11,'2020-11-01 08:28:18','Botals Sweep','8.00','Per Pice','9','Calss B','2000'),(12,'2020-11-07 07:20:14','Chamical RungCart','220.00','Per Kg','230','Class A','2'),(13,'2020-11-19 09:46:32','Jharoo Chota Tela','129.05','Per Pice','139.1','Class C','10'),(16,'2020-11-19 16:48:08','Chamical Dp200','2000.00','Per Letter','2600.00','Calss B','2'),(17,'2020-11-25 07:55:09','Wipper Floora','92.52','Per Pice','102.50','Calss B','10'),(18,'2020-11-30 07:51:08','Chamical Broza','120','Per Kg','125','Class C','5'),(21,'2020-12-05 09:01:40','Chamical ExtraPower','200','Per Unit','300','Class A','100'),(20,'2020-11-30 15:25:47','Chamical Castic','210','Per Kg','220','Calss B','5'),(22,'2020-12-12 14:19:46','Jharoo lambatela200gm','200.00','Per Pice','260.0','Class C','10');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_update`
--

DROP TABLE IF EXISTS `product_update`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_update` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_update`
--

LOCK TABLES `product_update` WRITE;
/*!40000 ALTER TABLE `product_update` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_update` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C3` varchar(225) DEFAULT NULL,
  `C4` text CHARACTER SET utf8 COLLATE utf8_bin,
  `C5` varchar(225) DEFAULT NULL,
  `C6` text CHARACTER SET utf8 COLLATE utf8_bin,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  `C9` varchar(225) DEFAULT NULL,
  `C10` varchar(225) DEFAULT NULL,
  `C11` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'2021-01-28 11:48:55','Jharoo',NULL,'LambaTela',NULL,'1539.96','Per Dozen','1719.60','Class A','20'),(2,'2021-01-28 11:48:55','Wipper',NULL,'Malta',NULL,'107.52','Per Pice','112.5','Calss B','10'),(3,'2021-01-28 11:48:55','Jharoo',NULL,'Phool',NULL,'57.51','Per Piece','67.5','Class A','10'),(4,'2021-01-28 11:48:55','Chamical',NULL,'ShampoBase',NULL,'624.00','Per Kg','630','Calss B','5'),(5,'2021-01-28 11:48:55','Chamical',NULL,'Tzab',NULL,'700','Per Can','800.00','Calss B','20'),(6,'2021-01-28 11:48:55','Chamical',NULL,'ColourRed',NULL,'1100.00','Per Kg','1200','Class C','5'),(7,'2021-01-28 11:48:55','Lable',NULL,'Lizol',NULL,'2.06','Per Pice','3','Class D','10'),(8,'2021-01-28 11:48:55','Jharoo',NULL,'Small',NULL,'360.00','Per Dozen','384.0','Class C','10'),(9,'2021-01-28 11:48:55','Botals',NULL,'Sweep',NULL,'8.00','Per Pice','9','Calss B','2000'),(10,'2021-01-28 11:48:55','Chamical',NULL,'RungCart',NULL,'220.00','Per Kg','230','Class A','2'),(11,'2021-01-28 11:48:55','Jharoo',NULL,'Chota',NULL,'129.05','Per Piece','139.1','Class C','10'),(12,'2021-01-28 11:48:55','Chamical',NULL,'Dp200',NULL,'2000.00','Per Letter','2600.00','Calss B','2'),(13,'2021-01-28 11:48:55','Wipper',NULL,'Floora',NULL,'92.52','Per Pice','120','Calss B','10'),(14,'2021-01-28 11:48:55','Chamical',NULL,'Broza',NULL,'120','Per Kg','125','Class C','5'),(15,'2021-01-28 11:48:55','Chamical',NULL,'ExtraPower',NULL,'200','Per Unit','300','Class A','100'),(16,'2021-01-28 11:48:55','Chamical',NULL,'Castic',NULL,'210','Per Kg','220','Calss B','5'),(17,'2021-01-28 11:48:55','Jharoo',NULL,'lambatela200gm',NULL,'130','Per Piece','200.00','Class C','10'),(18,'2021-01-30 10:02:36','Chamical',NULL,'Bleach',NULL,'130','Per Kg','100','Calss A','10'),(19,'2021-01-31 19:55:06','Lable',NULL,'Bleach',NULL,'1.5','Per Pice','2','Class A','10'),(20,'2021-01-31 19:55:29','Lable',NULL,'Neal',NULL,'1.2','Per Pice','2','Class A','10'),(21,'2021-02-01 04:00:05','Chamical',NULL,'Bleach',NULL,'120','Per Kg','150','Class A','10'),(24,'2021-02-01 04:42:02','Chamical',NULL,'kdjlksja',NULL,'100','Per Kg','120','Calss B','10'),(25,'2021-02-01 04:43:48','Botals',NULL,'Bleach 600ml',NULL,'8','Unit','9','Class A','10'),(26,'2021-02-01 05:13:45','Botals',NULL,'Neal',NULL,'1.5','Unit','10','Class A','10'),(27,'2021-02-01 05:14:29','Botals',NULL,'Phynial',NULL,'23','Unit','25','Class A','10');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profit`
--

DROP TABLE IF EXISTS `profit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profit` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profit`
--

LOCK TABLES `profit` WRITE;
/*!40000 ALTER TABLE `profit` DISABLE KEYS */;
INSERT INTO `profit` VALUES (12,'28','01','2021','10.0'),(11,'28','12','2020','49.9');
/*!40000 ALTER TABLE `profit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  `C9` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`C1`),
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (80,'29 17:07:43','12','2020','Kamran','575.1','500.0','75.1','500'),(79,'28 22:16:41','12','2020','Kamran','7200.0','7000.0','200.0','7000'),(78,'28 22:02:37','12','2020','Kamran','7000.0','7000.0','.0','7000'),(75,'28 17:27:39','12','2020','Kamran','632.6','600.0','32.6','600'),(74,'28 17:06:07','12','2020','Kamran','925.2','900.0','25.2','900'),(73,'28 17:03:10','12','2020','Kamran','1075.2','1000.0','75.2','1000');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_details`
--

DROP TABLE IF EXISTS `purchase_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_details` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_details`
--

LOCK TABLES `purchase_details` WRITE;
/*!40000 ALTER TABLE `purchase_details` DISABLE KEYS */;
INSERT INTO `purchase_details` VALUES (117,'80','Jharoo Phool','10','57.51','Per Pice','575.1'),(115,'78','Chamical Tzab','10','700','Per Can','7000.0'),(116,'79','Jharoo Small','20','360.00','Per Dozen','7200.0'),(112,'75','Jharoo Phool','11','57.51','Per Pice','632.6'),(111,'74','Wipper Floora','10','92.52','Per Pice','925.2'),(110,'73','Wipper Malta','10','107.52','Per Pice','1075.2');
/*!40000 ALTER TABLE `purchase_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratelist`
--

DROP TABLE IF EXISTS `ratelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratelist` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`C1`),
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratelist`
--

LOCK TABLES `ratelist` WRITE;
/*!40000 ALTER TABLE `ratelist` DISABLE KEYS */;
INSERT INTO `ratelist` VALUES (20,'Chamical Tzab','12121212','Per Dozen','2000'),(19,'Jharoo Phool','21221','Per gm','3000'),(21,'Lable Lizol','21221212','Per Pice','20000'),(22,'Botals Sweep','32323232','Per Pice','6000'),(23,'Botals Sweep','21212121','Per Unit','6000'),(24,'Chamical RungCart','32323232','Per Kg','1000');
/*!40000 ALTER TABLE `ratelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  `C9` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (53,'28 10:34:02','01','2021','Kamran','225.0','225.0','.0','225'),(52,'28 17:49:36','12','2020','Kamran','512.5','500.0','12.5','500');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_details`
--

DROP TABLE IF EXISTS `sale_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_details` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_details`
--

LOCK TABLES `sale_details` WRITE;
/*!40000 ALTER TABLE `sale_details` DISABLE KEYS */;
INSERT INTO `sale_details` VALUES (37,'53','Wipper Malta','2','112.5','Per Pice','225.0','10.0'),(36,'52','Wipper Floora','5','102.50','Per Pice','512.5','49.9');
/*!40000 ALTER TABLE `sale_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scale`
--

DROP TABLE IF EXISTS `scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`C1`),
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scale`
--

LOCK TABLES `scale` WRITE;
/*!40000 ALTER TABLE `scale` DISABLE KEYS */;
INSERT INTO `scale` VALUES (1,'Per Pice','1','2'),(2,'Per Dozen','12','1'),(3,'Per gm','1','4'),(4,'Per Kg','1000','3'),(5,'Per Letter','1000','6'),(6,'Per ml','1','5'),(7,'Per Can','1',''),(8,'Per Unit','1','');
/*!40000 ALTER TABLE `scale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`C1`),
  UNIQUE KEY `C1` (`C1`),
  UNIQUE KEY `C1_2` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (37,'2020-12-28 11:17:47','Kamran','12.5','408.1','0344-4200515','WahCantt','0');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_a`
--

DROP TABLE IF EXISTS `shop_a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_a` (
  `C1` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `C2` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C1` (`C1`)
) ENGINE=MyISAM AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_a`
--

LOCK TABLES `shop_a` WRITE;
/*!40000 ALTER TABLE `shop_a` DISABLE KEYS */;
INSERT INTO `shop_a` VALUES (115,'2021-01-28 05:34:22','Sale','Kamran','53'),(114,'2020-12-29 12:08:19','Purchase','Kamran','80'),(113,'2020-12-28 17:17:07','Purchase','Kamran','79'),(111,'2020-12-28 16:16:26','Purchase','Kamran','77'),(110,'2020-12-28 12:49:57','Sale','Kamran','52'),(112,'2020-12-28 17:02:49','Purchase','Kamran','78'),(108,'2020-12-28 12:27:59','Purchase','Kamran','75'),(107,'2020-12-28 12:06:22','Purchase','Kamran','74'),(106,'2020-12-28 12:03:26','Purchase','Kamran','73');
/*!40000 ALTER TABLE `shop_a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `C1` varchar(225) DEFAULT NULL,
  `C2` varchar(225) DEFAULT NULL,
  `C3` varchar(225) DEFAULT NULL,
  `C4` varchar(225) DEFAULT NULL,
  `C5` varchar(225) DEFAULT NULL,
  `C6` varchar(225) DEFAULT NULL,
  `C7` varchar(225) DEFAULT NULL,
  `C8` varchar(225) DEFAULT NULL,
  UNIQUE KEY `C3` (`C3`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('12','2020','Jharoo Small','0.0','20.0','20.0','7200','Per Dozen'),('12','2020','Chamical Tzab','0.0','10.0','10.0','7000','Per Can'),('12','2020','Wipper Floora','0.0','0.41666666666666696','0.41666666666666696','463','Per Dozen'),('12','2020','Jharoo Phool','0.0','1.75','1.75','1208','Per Dozen'),('01','2021','Wipper Malta','0.0','0.6666666666666667','0.6666666666666667','860','Per Dozen');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-01 12:32:36
