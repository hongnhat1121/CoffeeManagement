CREATE DATABASE  IF NOT EXISTS `coffeemanagementdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `coffeemanagementdb`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: coffeemanagementdb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `AccountID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Active` enum('LOCK','AVAILABLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'AVAILABLE',
  `Role` enum('USER','ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`AccountID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `AccountID_UNIQUE` (`AccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('019ca6fc-f542-4726-ae3c-39868e6f31f3','user21','cafc88296695f6d6c847261298cbfa0c22ba8f918c7f8d733c3b24036bb54181','AVAILABLE','USER'),('0d95bc5c-4b83-4b16-a53e-e21e2bcb0bea','admin27','45f80449db34eb0ca35502ca1e52fbae108c367510c547dd769018df8e4e1790','AVAILABLE','ADMIN'),('1e04d30c-92c9-480a-a8b2-f327d5068ac2','username','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','AVAILABLE','USER'),('32faff23-a42f-4ff1-bd91-c30d7437b2f2','anhkhoa','f1fa785cfaf149cbc22a31119ad5e61d0885ed3714f871715d4d9cd4a072f7b3','AVAILABLE','USER'),('5e87d02c-f5b7-45c2-afb2-dfea7341fa22','admin94','b74eddae78dbca5c268e3a51b06c29aa7992e3cbefcfc48918f47076faac3bc2','AVAILABLE','ADMIN'),('8a110593-f475-49e9-ba06-379f0ed97ea9','user2022','0e85282ab209f1090ecb4e36fadc6e3a7add5b0fc79a4220a0447f18197bb8ae','AVAILABLE','USER'),('ac047e8a-6ab8-4d35-9416-7be02ec22be3','admin90','503f1738a0ae1e2644293b1207986697a2ad4abae1dd249d40bd922f6831adbc','AVAILABLE','ADMIN'),('b5807aad-c423-44a5-a0f8-1fc8c2d2b424','user63','227b8b54f0400697863dbc95f93f132eae1d0c39cb22bc66e09aa1dc827a6945','AVAILABLE','USER'),('cc77cadd-96ce-4a61-beee-831474b4cd84','admin53','ff754a662c658ccf1459bb2fd16c4efe219df6953336f4a2b82ae6bc7edf133a','AVAILABLE','ADMIN'),('d19b5955-7df8-4e23-a669-ab5a3e6ae241','admin35','90a2739c4943063f233da3e46ddca747dcadbbf0714847bad0ba5960eb0fa4e9','AVAILABLE','ADMIN'),('f045d671-e83c-4b89-91cc-8304154ecf37','user100','611adf2c04cd259c88aa7463e9889dc6f9d0b201e863fe4e3b2997acdba015e2','AVAILABLE','USER'),('f0e08c27-93f0-45f9-87f0-a0bfc4fea214','diemphuong1994','88a8c603c79d0934f4b61be4fa7cdbbb48603a1bb9f5f5e1ec2cc92aa4e4f190','AVAILABLE','USER');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `EmployeeID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `FullName` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BirthDate` date NOT NULL,
  `Gender` enum('MALE','FEMALE','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IdentityCard` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `HireDate` date NOT NULL,
  `AccountID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`EmployeeID`,`AccountID`),
  UNIQUE KEY `IdentityCard_UNIQUE` (`IdentityCard`),
  UNIQUE KEY `EmployeeID_UNIQUE` (`EmployeeID`),
  UNIQUE KEY `AccountID_UNIQUE` (`AccountID`),
  KEY `fk_employees_accounts1_idx` (`AccountID`),
  CONSTRAINT `fk_employees_accounts` FOREIGN KEY (`AccountID`) REFERENCES `accounts` (`AccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('0fbbabeb-331a-4a03-8536-c3a9fbcd3381','Hoàng Vũ Thanh','2001-01-01','MALE','495839394','0984759372','Đồng Nai','2022-01-08','0d95bc5c-4b83-4b16-a53e-e21e2bcb0bea'),('23c74463-75ea-4835-b0b7-9e6545bac000','Nguyễn Thị Hồng Nhật','2001-10-10','FEMALE','342010930','0836479646','Đồng Tháp','2022-01-08','ac047e8a-6ab8-4d35-9416-7be02ec22be3'),('412ab25d-e885-42e0-9243-ecc2b17e7796','Phan Hoài Phương','2000-03-02','MALE','049585453433','0696845856','Bến Tre','2022-01-14','1e04d30c-92c9-480a-a8b2-f327d5068ac2'),('54cf6d95-fdff-4477-8237-805d07e90217','La Trung Hiếu','2001-11-29','MALE','720489574945','0947857493','Vũng Tàu','2022-01-08','d19b5955-7df8-4e23-a669-ab5a3e6ae241'),('98885571-a5a3-4390-84c7-6be660f84f5f','Nguyễn Thanh Định','2001-01-10','MALE','123456789','0867564566','TP Hồ Chí Minh','2022-01-08','cc77cadd-96ce-4a61-beee-831474b4cd84'),('c9491382-ree5-4920-ab42-c04fed63bc45','Trần Minh Thiện','2003-02-10','MALE','496848458','0875934953','Tiền Giang','2022-01-11','8a110593-f475-49e9-ba06-379f0ed97ea9'),('d03011d2-697e-46fd-9099-575313391aba','Trần Thị Diễm Phương','1994-01-05','FEMALE','103984834','0394875674','','2022-01-15','f0e08c27-93f0-45f9-87f0-a0bfc4fea214'),('e0fb727a-b7bc-4cd8-8275-ac00175fc648','Phan Anh Khoa','2001-08-17','MALE','398457831','0694845453','Hưng Thạnh, Đồng Tháp','2022-01-15','32faff23-a42f-4ff1-bd91-c30d7437b2f2');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `OrderID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ProductID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Quantity` int NOT NULL DEFAULT '1',
  `UnitPrice` decimal(10,0) NOT NULL,
  `Note` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`OrderID`,`ProductID`),
  KEY `fk_orders_has_products_products1_idx` (`ProductID`),
  KEY `fk_orders_has_products_orders1_idx` (`OrderID`),
  CONSTRAINT `fk_orders_has_products_orders1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `fk_orders_has_products_products1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES ('13a46c15-c294-456a-940c-334d18fd5b93','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,''),('1904402d-e4c1-4fc5-8df7-9277a81f7dca','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,''),('34b6d586-9e57-4fa9-877b-800eba85c63c','426e1568-bdb5-4736-b0a1-36b1ed28883d',1,19000,NULL),('34b6d586-9e57-4fa9-877b-800eba85c63c','4699124e-7aef-4607-84d8-ed10ffe474ff',1,29000,NULL),('34b6d586-9e57-4fa9-877b-800eba85c63c','4abb5eb4-c81f-4d67-9202-5f78e1ec8830',1,19000,NULL),('39a28953-15c6-48e1-b7a5-b8689b80ec80','06e8e958-8fc9-4edd-8aa4-9bdfb767024f',1,19000,NULL),('39a28953-15c6-48e1-b7a5-b8689b80ec80','37ab6968-0294-4178-b0b1-59a7855a8587',1,12000,NULL),('39a28953-15c6-48e1-b7a5-b8689b80ec80','3886b262-e547-4de4-9602-4810975d73e7',1,29000,NULL),('51bbb7f1-8a70-416f-94a6-8e997eff709f','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('51bbb7f1-8a70-416f-94a6-8e997eff709f','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,NULL),('8a7fa70a-3341-4174-a8d8-c1f4da755268','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('8a7fa70a-3341-4174-a8d8-c1f4da755268','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','06e8e958-8fc9-4edd-8aa4-9bdfb767024f',1,19000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','19a30714-104e-4a53-8107-f8286a5908f9',1,15000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','224a0092-3585-403f-bf69-e697894fde48',1,55000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','3886b262-e547-4de4-9602-4810975d73e7',1,29000,NULL),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','426e1568-bdb5-4736-b0a1-36b1ed28883d',1,19000,NULL),('9c90e564-6290-446d-8923-bf1c7903cfd5','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,''),('9ec4be6e-a592-467a-9058-fdb3be90e934','06e8e958-8fc9-4edd-8aa4-9bdfb767024f',1,19000,NULL),('9ec4be6e-a592-467a-9058-fdb3be90e934','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('9ec4be6e-a592-467a-9058-fdb3be90e934','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,NULL),('9ec4be6e-a592-467a-9058-fdb3be90e934','3188fca9-6cfb-4849-b652-f30dcd1a1826',1,55000,NULL),('9ec4be6e-a592-467a-9058-fdb3be90e934','37ab6968-0294-4178-b0b1-59a7855a8587',1,12000,NULL),('9ec4be6e-a592-467a-9058-fdb3be90e934','e4bf404b-d965-49f9-a155-a7bc3fda7bf7',1,29000,NULL),('a70f95e8-429c-441a-8adf-64c432bccafa','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,''),('b43395f8-3609-4f3c-a9e9-46e22e429b4c','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('ed6f8aa6-e35f-4a62-b045-59e9655a7066','17b34b86-0742-4df0-b008-3b4060381bd8',1,59000,''),('feb6edf7-c74d-41db-82f3-ac2922fa0d6c','16716ac7-ccff-40e1-afcd-d0717ff5e280',1,50000,NULL),('feb6edf7-c74d-41db-82f3-ac2922fa0d6c','426e1568-bdb5-4736-b0a1-36b1ed28883d',1,19000,NULL);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `OrderDate` date NOT NULL,
  `Total` decimal(10,0) NOT NULL,
  `Payment` int DEFAULT NULL,
  `TableID` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EmployeeID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`OrderID`),
  UNIQUE KEY `OrderID_UNIQUE` (`OrderID`),
  KEY `fk_orders_tables_idx` (`TableID`),
  KEY `fk_orders_employees_idx` (`EmployeeID`),
  CONSTRAINT `fk_orders_employees` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `fk_orders_tables` FOREIGN KEY (`TableID`) REFERENCES `tables` (`TableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('13a46c15-c294-456a-940c-334d18fd5b93','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('1904402d-e4c1-4fc5-8df7-9277a81f7dca','2022-01-14',59000,0,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('2ccfae3c-ec99-4ebb-8a91-ed63bd8518ae','2022-01-12',128000,0,'1b5e75c5-630e-4172-b8a0-e7a693255a9d','54cf6d95-fdff-4477-8237-805d07e90217'),('34b6d586-9e57-4fa9-877b-800eba85c63c','2022-01-13',124000,0,'0a5cf1dd-9e1f-464f-897c-0d219a6d5b2c','54cf6d95-fdff-4477-8237-805d07e90217'),('39a28953-15c6-48e1-b7a5-b8689b80ec80','2022-01-14',60000,0,'13730cf6-fc5b-4a1d-b671-8a340a035e5e','54cf6d95-fdff-4477-8237-805d07e90217'),('51bbb7f1-8a70-416f-94a6-8e997eff709f','2022-01-14',109000,0,'1b5e75c5-630e-4172-b8a0-e7a693255a9d','54cf6d95-fdff-4477-8237-805d07e90217'),('7000f44a-4be1-4354-984f-d6145cc443ce','2022-01-12',192000,1,'0a5cf1dd-9e1f-464f-897c-0d219a6d5b2c','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('7a2ec563-4c12-4162-8903-72fae50452d8','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('8a7fa70a-3341-4174-a8d8-c1f4da755268','2022-01-14',109000,1,'1a213886-f7e0-4d67-8a3e-b9d60af2130c','54cf6d95-fdff-4477-8237-805d07e90217'),('93272fed-d9ee-4380-9ab5-ea625cfa6e67','2022-01-14',246000,1,'0a5cf1dd-9e1f-464f-897c-0d219a6d5b2c','54cf6d95-fdff-4477-8237-805d07e90217'),('9c90e564-6290-446d-8923-bf1c7903cfd5','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('9ec4be6e-a592-467a-9058-fdb3be90e934','2022-01-12',224000,1,'1328d5cd-9b66-4243-aafa-dad32bf6b718','54cf6d95-fdff-4477-8237-805d07e90217'),('a70f95e8-429c-441a-8adf-64c432bccafa','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('aa5cb5b1-be73-4cc4-b12e-6a493ac94f81','2022-01-12',81000,1,'1328d5cd-9b66-4243-aafa-dad32bf6b718','54cf6d95-fdff-4477-8237-805d07e90217'),('b3d929af-5457-49ea-9050-ecb4bff1f503','2022-01-12',109000,1,'0a5cf1dd-9e1f-464f-897c-0d219a6d5b2c','54cf6d95-fdff-4477-8237-805d07e90217'),('b43395f8-3609-4f3c-a9e9-46e22e429b4c','2022-01-12',50000,1,'1328d5cd-9b66-4243-aafa-dad32bf6b718','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('b85fbde8-c76f-4faa-9771-8363a854b271','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('d45f9d60-ad0c-419e-aca6-168b2251c544','2022-01-12',121000,1,'13730cf6-fc5b-4a1d-b671-8a340a035e5e','54cf6d95-fdff-4477-8237-805d07e90217'),('ed6f8aa6-e35f-4a62-b045-59e9655a7066','2022-01-14',59000,1,'5713adde-002b-46d7-bcaf-03ff01c5ac39','0fbbabeb-331a-4a03-8536-c3a9fbcd3381'),('feb6edf7-c74d-41db-82f3-ac2922fa0d6c','2022-01-13',69000,1,'1328d5cd-9b66-4243-aafa-dad32bf6b718','54cf6d95-fdff-4477-8237-805d07e90217');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ProductName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UnitPrice` decimal(10,0) NOT NULL,
  `Category` enum('FOOD','DRINK') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductID_UNIQUE` (`ProductID`),
  UNIQUE KEY `ProductName_UNIQUE` (`ProductName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('06e8e958-8fc9-4edd-8aa4-9bdfb767024f','Bánh chuối',19000,'FOOD'),('16716ac7-ccff-40e1-afcd-d0717ff5e280','Trà Sữa Oolong Nướng Nóng',50000,'DRINK'),('17b34b86-0742-4df0-b008-3b4060381bd8','Latte Táo Lê Quế Đá',59000,'DRINK'),('19a30714-104e-4a53-8107-f8286a5908f9','Cà phê sữa đá',15000,'DRINK'),('224a0092-3585-403f-bf69-e697894fde48','Hồng Trà Latte Macchiato',55000,'DRINK'),('3188fca9-6cfb-4849-b652-f30dcd1a1826','Trà Sữa Oolong Nướng Trân Châu',55000,'DRINK'),('37ab6968-0294-4178-b0b1-59a7855a8587','Bánh Mì Que Pate Cay',12000,'FOOD'),('3886b262-e547-4de4-9602-4810975d73e7','Bánh tiramisu',29000,'FOOD'),('3bda9445-b565-4678-b15b-8d31d23658d4','Trà sữa Masala Chai Trân Châu Đá',59000,'DRINK'),('3e5d71ca-4491-413b-b905-6e35d124d8a4','Latte Táo Lê Quế Nóng',59000,'DRINK'),('426e1568-bdb5-4736-b0a1-36b1ed28883d','Bánh mì thịt nướng',19000,'FOOD'),('4699124e-7aef-4607-84d8-ed10ffe474ff','Bánh Mì VN Thịt Nguội',29000,'FOOD'),('4abb5eb4-c81f-4d67-9202-5f78e1ec8830','Bánh mì Gà xé nước tương',19000,'FOOD'),('4b98159e-1139-4d8a-bb28-8af4a350a9a7','Caramel Macchiato',59000,'DRINK'),('5330c720-e7e3-41bc-8f9d-42942daea9b3','Bánh Mousse Ca Cao',29000,'FOOD'),('5eaae8d8-a199-49de-9db7-c9c5ce084204','Mocha Nóng',50000,'DRINK'),('5ec9b4d0-68e9-4481-853d-c4ff1d408098','Bánh phô mai cà phê',29000,'FOOD'),('621626cb-930f-4998-9e44-2185d2b85261','Phin đen đá',29000,'DRINK'),('63db0670-d705-4285-b453-3dcd18c2a90a','PhinDi Hạnh Nhân',39000,'DRINK'),('6dcf881a-2a6b-41b8-add3-00172b0b2918','Trà thanh đào',39000,'DRINK'),('6fb69575-5d14-4bff-8e92-ecde71a81376','Bánh sô-cô-la Highlands',29000,'FOOD'),('7832c2a7-c5ce-42e8-9919-1d51d587df26','Bánh mì chả lụa xá xíu',19000,'FOOD'),('78e3870b-48af-4526-af53-f081518ce2c1','Latte Đá',50000,'DRINK'),('7cfb2cd3-464f-4edf-ba59-81ca94f436f8','Trà Sữa Mắc Ca Trân Châu Trắng',50000,'DRINK'),('7f4c14c2-56e9-4922-bf71-807ca244c31d','Chà Bông Phô Mai',32000,'FOOD'),('81dc7f4a-7fdc-4ca0-bf03-41355ad27671','Espresso Đá',45000,'DRINK'),('836c615b-78d3-40d5-99a8-bdee9dc6bb9a','PhinDi Choco',39000,'DRINK'),('861c7565-a284-4b35-8009-112cef0f4e36','Trà sữa Masala Chai Nóng',59000,'DRINK'),('87acda42-d370-477e-962c-cdabf6ef787c','Espresso Nóng',40000,'DRINK'),('895253b9-c113-4401-8c0d-f0f883ab01bf','Mocha Đá',50000,'DRINK'),('8a6fa0fc-276c-48e8-a997-307e2b08d77d','Mì cay',45000,'FOOD'),('8bcc198c-7335-4e51-b915-936c4685c72e','Mocha Macchiato',59000,'DRINK'),('93bfb37f-a14f-47dd-8254-bbbbf014a790','Trà thạch vải',39000,'DRINK'),('94c8de04-3447-4882-9be5-423ddf72dfaf','Americano',44000,'DRINK'),('98d082b9-7c3f-42f8-b7ca-21f87de30e65','Bánh mì xíu mại',19000,'FOOD'),('9b985552-ab51-4dde-aa17-4c3baebe4c56','Trà Đen Macchiato',50000,'DRINK'),('9e273625-c6fc-4eee-bde3-ff7911892ff0','Cappuccino Nóng',50000,'DRINK'),('a1c39191-303a-4543-89e6-21022bcaaf66','PhinDi Kem Sữa',39000,'DRINK'),('a31fe73c-d8c5-4da7-87b7-5b7d3189837c','Cappuccino Đá',50000,'DRINK'),('a5b95a78-b4d4-4132-859a-93f070b84027','Bánh Mì Que Pate',12000,'FOOD'),('ba0e7feb-f632-4bc1-b78f-25e580431a15','Hồng Trà Sữa Trân Châu',55000,'DRINK'),('bf9fd292-688a-4f48-a4d2-6256d6d5742f','Bánh phô mai chanh dây',29000,'FOOD'),('c37cd398-f1bf-4139-b158-6f418ed24478','Hồng Trà Sữa Nóng',50000,'DRINK'),('c383aaa1-e994-4965-8ac5-d57ad4a6483e','Mousse Passion Cheese',29000,'FOOD'),('c9491382-ree5-4920-ab42-c04fed63bc45','Hồng trà',27000,'DRINK'),('cf66e921-07eb-4142-b7fa-9aa73320ed17','Latte Nóng',40000,'DRINK'),('d16f7cc1-a549-43e7-bb33-1aa3e81510fa','Trà sen vàng',39000,'DRINK'),('d349f044-f484-48ad-82fb-d3357bb52d3c','Mousse Gấu Chocolate',39000,'FOOD'),('d3bec52c-a55e-4612-94b7-4b22df887bb6','Bánh caramel phô mai',29000,'FOOD'),('da05e2de-8cd8-4e3b-b83e-1620194a9565','Phin đen nóng',29000,'DRINK'),('e4bf404b-d965-49f9-a155-a7bc3fda7bf7','Bánh phô mai trà xanh',29000,'FOOD'),('e4fc7ff4-507a-43cc-a1af-8cbbbb1a65ca','Bánh mousse đào',29000,'FOOD'),('f0ceea24-8c5c-4dcd-8c9d-20c2a9fed992','Phin sữa đá',29000,'DRINK'),('f6560b45-52d8-4433-b123-fc30937f76e6','Mousse Red Velvet',29000,'FOOD'),('f6d28871-30b4-41be-84fc-5413686d1195','Croissant Trứng Muối',35000,'FOOD'),('f8af10c0-ead0-4002-8d67-deb9bb658ff3','Mousse Tiramisu',32000,'FOOD');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables` (
  `TableID` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TableName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Capacity` int NOT NULL,
  `Status` enum('EMPTY','FULL') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'EMPTY',
  PRIMARY KEY (`TableID`),
  UNIQUE KEY `TableName_UNIQUE` (`TableName`),
  UNIQUE KEY `TableID_UNIQUE` (`TableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES ('0a5cf1dd-9e1f-464f-897c-0d219a6d5b2c','Bàn 012',5,'EMPTY'),('1328d5cd-9b66-4243-aafa-dad32bf6b718','Bàn 044',8,'EMPTY'),('13730cf6-fc5b-4a1d-b671-8a340a035e5e','Bàn 002',2,'FULL'),('1a213886-f7e0-4d67-8a3e-b9d60af2130c','Bàn 117',4,'FULL'),('1b5e75c5-630e-4172-b8a0-e7a693255a9d','Bàn 026',4,'EMPTY'),('1f0689e1-e43f-4e6a-b24d-fbc3d2ee81d0','Bàn 019',12,'EMPTY'),('2b8026fb-1472-4528-9ad4-0de2a77815d8','Bàn 048',3,'EMPTY'),('5713adde-002b-46d7-bcaf-03ff01c5ac39','Bàn 005',4,'EMPTY'),('5bd421c0-b78e-4f53-b156-74e833a1b1b8','Bàn 031',2,'EMPTY'),('5ecd02fd-0ac7-4459-b4da-60d74d52976f','Bàn 014',10,'EMPTY'),('738b1d04-eda7-4d14-9d65-b7b812128bf1','Bàn 007',3,'EMPTY'),('75f9be49-4fd4-4423-a52b-be7c93aed58a','Bàn 029',8,'EMPTY'),('77fc12c0-232a-4185-a134-5b7ec3b6b0bb','Bàn 032',10,'EMPTY'),('7af26993-87d6-4660-8e1e-37e33739f00b','Bàn 050',2,'EMPTY'),('824c5da4-bdfe-4532-b02d-9867fe7a7a52','Bàn 008',2,'EMPTY'),('952800ed-f7ef-4866-8d63-be88444d60dd','Bàn 043',6,'EMPTY'),('96836ee0-d9fb-4497-b1a2-3fdd42202f2c','Bàn 045',5,'EMPTY'),('ba8cdf46-3f42-403c-a98e-e4b8d874cb57','Bàn 010',4,'EMPTY'),('bb54c38e-09c3-4239-91de-187f7a5b5764','Bàn 020',4,'EMPTY'),('bfcca314-050e-4f01-8620-b2b56e838ff8','Bàn 003',8,'EMPTY'),('c9491382-ree5-4920-ab42-c04fed63bc45','Bàn 123',5,'EMPTY'),('c9591382-ree5-4920-ab42-c04fed63bc45','Bàn 073',5,'EMPTY'),('dd5ca240-2384-4dc5-97dc-d14d79251fd9','Bàn 035',2,'EMPTY'),('e3b90dea-a63c-443d-82f1-1ac19bb46914','Bàn 037',8,'EMPTY'),('eb7075f4-a8f6-459d-bd9f-d0418eb8e769','Bàn 021',5,'EMPTY');
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-15 22:45:10
