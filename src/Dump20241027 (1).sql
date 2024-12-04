-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: hospital
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `Appointment_ID` int NOT NULL,
  `Patient_ID` int DEFAULT NULL,
  `Doctor_ID` int DEFAULT NULL,
  `Appointment_Date` date DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Appointment_ID`),
  KEY `Patient_ID` (`Patient_ID`),
  KEY `Doctor_ID` (`Doctor_ID`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (9001,1001,2001,'2024-10-01','Concluded'),(9002,1002,2002,'2024-10-02','Waiting'),(9003,1003,2003,'2024-10-03','Concluded'),(9004,1004,2004,'2024-10-04','Concluded'),(9005,1005,2001,'2024-10-05','Waiting'),(9006,1006,2002,'2024-10-06','Concluded'),(9007,1007,2003,'2024-10-07','Waiting'),(9008,1008,2004,'2024-10-08','Concluded'),(9009,1009,2001,'2024-10-09','Waiting'),(9010,1010,2002,'2024-10-10','Concluded'),(9011,1011,2003,'2024-10-11','Waiting'),(9012,1012,2004,'2024-10-12','Concluded');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing` (
  `Bill_ID` int NOT NULL,
  `Patient_ID` int DEFAULT NULL,
  `Total_Amount` decimal(10,2) DEFAULT NULL,
  `Payment_Status` varchar(50) DEFAULT NULL,
  `Date_Issued` date DEFAULT NULL,
  PRIMARY KEY (`Bill_ID`),
  KEY `Patient_ID` (`Patient_ID`),
  CONSTRAINT `billing_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing`
--

LOCK TABLES `billing` WRITE;
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
INSERT INTO `billing` VALUES (7701,1001,NULL,'Paid','2024-10-01'),(7702,1002,NULL,'Pending','2024-10-05'),(7703,1003,NULL,'Overdue','2024-10-10'),(7704,1001,NULL,'Paid','2024-10-15'),(7705,1004,NULL,'Paid','2024-10-20'),(7706,1002,NULL,'Pending','2024-10-25'),(7707,1003,NULL,'Overdue','2024-10-28');
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `Doctor_ID` int NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Specialty` varchar(255) DEFAULT NULL,
  `Experience` int DEFAULT NULL,
  `Contact_Info` varchar(255) DEFAULT NULL,
  `Department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Doctor_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (2001,'Dr. Abdullah Nazeer ','Neurology',12,'Ph No. 8978453212  Email abdllna@gmail.com','Neurology'),(2002,'Dr. Juneyd Kareem ','Endocrinology',8,'Ph No. 9898789899 Email gcdc@gmail.com','Therapy'),(2003,'Dr. Umer Habib ','Neurosurgery',15,'Ph No. 9798489821 Email lmno@gmail.com','Surgery'),(2004,'Dr. Khalil Ahmed ','Pediatrics',21,'Ph No. 916859990 Email qwer@gmail.com','Intensive Care'),(2005,'Dr. Ahmed Khan','Cardiology',10,'03001234567, ahmed.khan@gmail.com','Heart'),(2006,'Dr. Fatima Ali','Pediatrics',8,'03101234567, fatima.ali@gmail.com','Children'),(2007,'Dr. Bilal Shah','Neurology',12,'03201234567, bilal.shah@gmail.com','Nervous System'),(2008,'Dr. Aisha Khan','Dermatology',5,'03301234567, aisha.bibi@gmail.com','Skin'),(2009,'Dr. Usman Tariq','Orthopedics',15,'03401234567, usman.tariq@gmail.com','Bones'),(2010,'Dr. Sana Malik','Gynecology',7,'03501234567, sana.malik@gmail.com','Women\'s Health'),(2011,'Dr. Zain Abbas','General Medicine',20,'03601234567, zain.abbas@gmail.com','General'),(2012,'Dr. Rehana Bashir','Psychiatry',9,'03701234567, rehanna.bashir@gmail.com','Mental Health'),(2013,'Dr. Kamran Hesam','Oncology',11,'03801234567, kamran.rizvi@gmail.com','Cancer'),(2014,'Dr. Sara Javed','Endocrinology',6,'03901234567, sara.javed@gmail.com','Hormones');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `Patient_ID` int NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Date_of_Birth` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Contact_Info` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Medical_History` text,
  PRIMARY KEY (`Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1001,'Amin Dar','1985-05-15','Male','1234567890','123 Khanyar','No major history'),(1002,'Shabnam Lone','1990-07-22','Female','0987654321','456 Rainawari','Allergic to penicillin'),(1003,'Iqbal Bhat','1978-02-10','Male','1122334455','789 Habba Kadal','Diabetic'),(1004,'Farhat Jan','1982-11-30','Female','2233445566','101 Ganderbal','Hypertension'),(1005,'Zara Khan','1995-09-18','Female','3344556677','202 Bemina','Asthma'),(1006,'Bilal Shah','1988-01-15','Male','4455667788','303 Lal Chowk','No major history'),(1007,'Riyaz Mir','1979-06-22','Male','5566778899','404 Pampore','Chronic back pain'),(1008,'Sabeena Qadri','2000-04-10','Female','6677889900','505 Baramulla','No major history'),(1009,'Sameer Wani','1992-10-05','Male','7788990011','606 Sopore','History of migraines'),(1010,'Humaira Naqash','1985-12-08','Female','8899001122','707 Pulwama','Anemia'),(1011,'Raashid Teli','1991-03-25','Male','9900112233','808 Anantnag','No major history'),(1012,'Shazia Hanjura','1998-08-14','Female','1122334455','909 Budgam','Lactose intolerant');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacy` (
  `Medicine_ID` int NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Stock_Level` int DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `Last_Updated` date DEFAULT NULL,
  PRIMARY KEY (`Medicine_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (101,'Paracetamol',150,10.00,'2024-10-25'),(102,'Amoxicillin',80,25.00,'2024-10-26'),(103,'Ibuprofen',100,15.00,'2024-10-24'),(104,'Metformin',60,20.00,'2024-10-23'),(105,'Atorvastatin',50,18.00,'2024-10-22'),(106,'Lisinopril',90,12.00,'2024-10-21'),(107,'Cetirizine',200,8.00,'2024-10-20'),(108,'Omeprazole',70,17.00,'2024-10-19'),(109,'Aspirin',120,11.00,'2024-10-18'),(110,'Losartan',40,22.00,'2024-10-17'),(111,'Levothyroxine',75,19.00,'2024-10-16'),(112,'Insulin',30,50.00,'2024-10-15');
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `Prescription_ID` int NOT NULL,
  `Appointment_ID` int DEFAULT NULL,
  `Medication` varchar(255) DEFAULT NULL,
  `Dosage` varchar(50) DEFAULT NULL,
  `Instructions` text,
  `Total_Cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Prescription_ID`),
  KEY `Appointment_ID` (`Appointment_ID`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`Appointment_ID`) REFERENCES `appointment` (`Appointment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (4401,9001,'Paracetamol',NULL,'Take one tablet every 6 hours. Drink plenty of water.',50.00),(4402,9002,'Amoxicillin',NULL,'Take one capsule every 8 hours. Complete the full course.',120.00),(4403,9003,'Ibuprofen',NULL,'Take one tablet every 8 hours after meals. Avoid if allergic.',75.00),(4404,9004,'Metformin',NULL,'Take one tablet twice daily. Monitor blood sugar regularly.',200.00),(4405,9005,'Atorvastatin',NULL,'Take one tablet at bedtime. Follow a balanced diet.',150.00),(4406,9006,'Lisinopril',NULL,'Take one tablet daily. Monitor blood pressure.',100.00),(4407,9007,'Cetirizine',NULL,'Take one tablet daily at bedtime. May cause drowsiness.',30.00),(4408,9008,'Omeprazole',NULL,'Take one capsule before breakfast. Avoid spicy foods.',90.00),(4409,9009,'Aspirin',NULL,'Take one tablet daily. Consult if any side effects occur.',60.00),(4410,9010,'Losartan',NULL,'Take one tablet daily. Regularly check blood pressure.',130.00),(4411,9011,'Levothyroxine',NULL,'Take on an empty stomach each morning. Wait 30 minutes before eating.',80.00),(4412,9012,'Insulin',NULL,'Inject as directed. Monitor blood sugar before each dose.',300.00);
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-27 23:32:10
