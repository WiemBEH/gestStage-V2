-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: offres_stage
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

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
-- Table structure for table `candidature`
--

DROP TABLE IF EXISTS `candidature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidature` (
  `offre` int(11) NOT NULL DEFAULT '0',
  `etudiant` varchar(255) NOT NULL DEFAULT '',
  `chemin_cv` varchar(255) DEFAULT NULL,
  `motivation` varchar(255) DEFAULT NULL,
  `reponse` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`offre`,`etudiant`),
  KEY `etudiant` (`etudiant`),
  CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`offre`) REFERENCES `offre_stage` (`id`),
  CONSTRAINT `candidature_ibfk_2` FOREIGN KEY (`etudiant`) REFERENCES `utilisateur` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidature`
--

LOCK TABLES `candidature` WRITE;
/*!40000 ALTER TABLE `candidature` DISABLE KEYS */;
INSERT INTO `candidature` VALUES (1,'wiem@ffa.fr','20170424213717-anglais10','message de motivation',1),(2,'wiem@ffa.fr','20170425123826-ODR SFR N°16164_101113.pdf','bhy',1),(3,'wiem@ffa.fr','20170425001523-CV.html','Ma motivation',0),(4,'wiem@ffa.fr','20170425124725-BEN EL HADJ Wiem CV(STID).pdf','hhy',NULL),(5,'wiem@ffa.fr','20170425115924-logo.png','je suis ghada',NULL),(6,'Abir@java.fr','20170425122358-rapport.pdf','gfd',1);
/*!40000 ALTER TABLE `candidature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domaine`
--

DROP TABLE IF EXISTS `domaine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domaine` (
  `domaine` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`domaine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domaine`
--

LOCK TABLES `domaine` WRITE;
/*!40000 ALTER TABLE `domaine` DISABLE KEYS */;
INSERT INTO `domaine` VALUES ('Développement Java'),('Géometrie'),('Histoire'),('Informatique'),('marketing'),('Mathématiques'),('Réseau'),('Statistiques'),('système');
/*!40000 ALTER TABLE `domaine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre_stage`
--

DROP TABLE IF EXISTS `offre_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offre_stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entreprise` varchar(255) DEFAULT NULL,
  `domaine` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `duree` int(11) DEFAULT NULL,
  `chemin_stockage` varchar(255) DEFAULT NULL,
  `descriptif` varchar(510) DEFAULT NULL,
  `valide` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `entreprise` (`entreprise`),
  KEY `domaine` (`domaine`),
  CONSTRAINT `offre_stage_ibfk_1` FOREIGN KEY (`entreprise`) REFERENCES `utilisateur` (`mail`),
  CONSTRAINT `offre_stage_ibfk_2` FOREIGN KEY (`domaine`) REFERENCES `domaine` (`domaine`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre_stage`
--

LOCK TABLES `offre_stage` WRITE;
/*!40000 ALTER TABLE `offre_stage` DISABLE KEYS */;
INSERT INTO `offre_stage` VALUES (1,'abe@kantena.com','Développement Java','Stage en j2EE','2017-05-01',90,'20170424213107-logo.png','Stage pour L3 en J2EE',1),(2,'abe@kantena.com','Informatique','Algorithmique','2017-06-08',90,'20170425001206-fond-gris.jpg','Description offre',1),(3,'abe@kantena.com','Statistiques','Analyse','2017-06-16',65,'20170425001249-anonymous.jpg','offre',1),(4,'abe@kantena.com','Mathématiques','stage l3 maths','2017-11-28',30,'20170425001352-fond-gris.jpg','Description...',1),(5,'abe@kantena.com','Développement Java','fghsd','2017-04-30',60,'20170425114347-CV.html','stage',1),(6,'abe@kantena.com','Développement Java','stage','2017-04-18',56,'20170425122003-DM1.pdf','dc',1);
/*!40000 ALTER TABLE `offre_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `mail` varchar(255) NOT NULL DEFAULT '',
  `mot_de_passe` varchar(15) NOT NULL,
  `droit` varchar(15) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `adresseRue` varchar(255) DEFAULT NULL,
  `adresseVille` varchar(255) DEFAULT NULL,
  `adresseCodePostal` varchar(255) DEFAULT NULL,
  `secteurActivite` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES ('abe@kantena.com','18011990','Entreprise','KANTENA',NULL,'169 241 312','20 rue Vlaminck','Grigny','91350','Développement Java'),('Abir@java.fr','18011990','Etudiant','abir','1990-04-26','542 565 853',NULL,NULL,NULL,'Réseau'),('Accor@java.fr','18011990','Entreprise','Accor',NULL,'789 854 652','10 Rue Aillaud','Evry','91000','Réseau'),('brala@gmail.com','doul','Entreprise','Evry',NULL,'754 578 995','cgnhfh','ghf','fghf','Développement Java'),('ghaa@elkamel.fr','1234','Entreprise','edf',NULL,'258 756 898','8 rue raspail','paris','7505','Statistiques'),('ghada@java.com','18011990','Administrateur','',NULL,NULL,NULL,NULL,NULL,NULL),('SFR@java.fr','18011990','Entreprise','SFR',NULL,'656 565 656','10 Rue emilie','Paris','75001','Développement Java'),('Wa@java.fr','18011990','Etudiant','BEN','1983-04-08','565 656 565',NULL,NULL,NULL,'Développement Java'),('wiem@ffa.fr','18011990','Etudiant','BEN EL HADJ','1994-04-08','65 846 515',NULL,NULL,NULL,'Développement Java');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-30  2:35:13
