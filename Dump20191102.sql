CREATE DATABASE  IF NOT EXISTS `restaurant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restaurant`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `desserts`
--

DROP TABLE IF EXISTS `desserts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `desserts` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desserts`
--

LOCK TABLES `desserts` WRITE;
/*!40000 ALTER TABLE `desserts` DISABLE KEYS */;
INSERT INTO `desserts` VALUES ('Десерты','Тирамису',3.5,180),('Десерты','Эклер',2.5,65);
/*!40000 ALTER TABLE `desserts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drinks`
--

DROP TABLE IF EXISTS `drinks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drinks` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drinks`
--

LOCK TABLES `drinks` WRITE;
/*!40000 ALTER TABLE `drinks` DISABLE KEYS */;
INSERT INTO `drinks` VALUES ('Напитки','Кофе',3,250),('Напитки','Чай',2.5,250);
/*!40000 ALTER TABLE `drinks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firstcourse`
--

DROP TABLE IF EXISTS `firstcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `firstcourse` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firstcourse`
--

LOCK TABLES `firstcourse` WRITE;
/*!40000 ALTER TABLE `firstcourse` DISABLE KEYS */;
INSERT INTO `firstcourse` VALUES ('Первое блюдо','Красный борщ',3,300),('Первое блюдо','Суп Харчо',3.5,300);
/*!40000 ALTER TABLE `firstcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garnish`
--

DROP TABLE IF EXISTS `garnish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `garnish` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garnish`
--

LOCK TABLES `garnish` WRITE;
/*!40000 ALTER TABLE `garnish` DISABLE KEYS */;
INSERT INTO `garnish` VALUES ('Гарниры','Плов с бараниной',5,270),('Гарниры','Пюре картофельное',2.3,250);
/*!40000 ALTER TABLE `garnish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotdishes`
--

DROP TABLE IF EXISTS `hotdishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotdishes` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotdishes`
--

LOCK TABLES `hotdishes` WRITE;
/*!40000 ALTER TABLE `hotdishes` DISABLE KEYS */;
INSERT INTO `hotdishes` VALUES ('Горячие блюда','Драники со сметаной',2.5,280),('Горячие блюда','Котлета по-киевски',4,120),('Горячие блюда','Куриное филе',3,100);
/*!40000 ALTER TABLE `hotdishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order33`
--

DROP TABLE IF EXISTS `order33`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order33` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order33`
--

LOCK TABLES `order33` WRITE;
/*!40000 ALTER TABLE `order33` DISABLE KEYS */;
INSERT INTO `order33` VALUES ('Гарниры','Плов с бараниной',5,270,1),('Холодные закуски','Салат Цезарь',2.9,150,1),('Первое блюдо','Суп Харчо',3.5,300,1),('Напитки','Чай',2.5,250,1),('Десерты','Эклер',2.5,65,2);
/*!40000 ALTER TABLE `order33` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order34`
--

DROP TABLE IF EXISTS `order34`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order34` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order34`
--

LOCK TABLES `order34` WRITE;
/*!40000 ALTER TABLE `order34` DISABLE KEYS */;
INSERT INTO `order34` VALUES ('Гарниры','Пюре картофельное',2.3,250,2),('Напитки','Чай',2.5,250,1),('Десерты','Эклер',2.5,65,1);
/*!40000 ALTER TABLE `order34` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order35`
--

DROP TABLE IF EXISTS `order35`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order35` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order35`
--

LOCK TABLES `order35` WRITE;
/*!40000 ALTER TABLE `order35` DISABLE KEYS */;
/*!40000 ALTER TABLE `order35` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order36`
--

DROP TABLE IF EXISTS `order36`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order36` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order36`
--

LOCK TABLES `order36` WRITE;
/*!40000 ALTER TABLE `order36` DISABLE KEYS */;
INSERT INTO `order36` VALUES ('Гарниры','Плов с бараниной',5,270,1),('Холодные закуски','Салат Цезарь',2.9,150,1),('Напитки','Чай',2.5,250,1);
/*!40000 ALTER TABLE `order36` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order37`
--

DROP TABLE IF EXISTS `order37`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order37` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order37`
--

LOCK TABLES `order37` WRITE;
/*!40000 ALTER TABLE `order37` DISABLE KEYS */;
INSERT INTO `order37` VALUES ('Первое блюдо','Красный борщ',3,300,1),('Десерты','Тирамису',3.5,180,2);
/*!40000 ALTER TABLE `order37` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(32) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (33,'2019-06-04 16:31:13'),(34,'2019-06-05 20:51:01'),(35,'2019-06-05 20:51:01'),(36,'2019-09-13 13:46:22'),(37,'2019-09-13 13:46:38');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salads`
--

DROP TABLE IF EXISTS `salads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `salads` (
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `price` double NOT NULL,
  `output` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salads`
--

LOCK TABLES `salads` WRITE;
/*!40000 ALTER TABLE `salads` DISABLE KEYS */;
INSERT INTO `salads` VALUES ('Холодные закуски','Салат Оливье',3.4,150),('Холодные закуски','Салат Цезарь',2.9,150);
/*!40000 ALTER TABLE `salads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `position` varchar(45) NOT NULL,
  `salary` double NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `menuAccess` tinyint(4) NOT NULL,
  `menuReadonly` tinytext NOT NULL,
  `staffAccess` tinytext NOT NULL,
  `staffReadonly` tinytext NOT NULL,
  `ordersAccess` tinytext NOT NULL,
  `ordersReadonly` tinytext NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('Чижик','Евгений','Леонидович','2000-03-18','Шеф-повар',499,'eugenium','1984',1,'0','1','1','1','0'),('Дорофеев','Валентин','Игоревич','2000-02-16','Администратор',500,'komilfo','1312',1,'0','1','0','1','0'),('Пупкин','Василий','Иванович','1996-06-27','Официант',300,'pupkin','vasya',0,'0','0','0','1','1');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-02 22:53:06
