-- Base de données :  offres_stage

DROP DATABASE offres_stage;
CREATE DATABASE offres_stage;

USE offres_stage; 

-- Structure de la table `utilisateur`
CREATE TABLE utilisateur (
  	mail VARCHAR(255), 
	mot_de_passe VARCHAR(15) NOT NULL,
	droit VARCHAR(15) NOT NULL,
  	nom VARCHAR(255) NOT NULL,
  	date_naissance DATE DEFAULT NULL,
  	telephone VARCHAR(255) DEFAULT NULL,
	adresseRue VARCHAR(255) DEFAULT NULL,
	adresseVille VARCHAR(255) DEFAULT NULL,
	adresseCodePostal VARCHAR(255) DEFAULT NULL,
	secteurActivite VARCHAR(255) DEFAULT NULL,
  	CONSTRAINT CHECK_droit CHECK(droit IN ('Administrateur','Etudiant', 'Entreprise')),
  	PRIMARY KEY (mail)  	
); 

-- Structure de la table `domaine`
CREATE TABLE domaine (
  	domaine VARCHAR(50),
  	PRIMARY KEY (domaine)
);


-- Structure de la table `offre_stage`
CREATE TABLE offre_stage (
	id INT AUTO_INCREMENT,
	entreprise VARCHAR(255),
	domaine VARCHAR(255),
	libelle VARCHAR(255) NOT NULL,
	date_debut DATE,
	duree INT,
	chemin_stockage VARCHAR(255),
	descriptif VARCHAR(510),
	FOREIGN KEY (entreprise) REFERENCES utilisateur(mail),
	FOREIGN KEY (domaine) REFERENCES domaine(domaine),
	PRIMARY KEY (id)
);


-- Structure de la table `candidature`
CREATE TABLE candidature (
	offre INT,
	etudiant VARCHAR(255),
	chemin_cv VARCHAR(255),
	motivation VARCHAR(255), 
	reponse BOOL,
	FOREIGN KEY (offre) REFERENCES offre_stage(id),
	FOREIGN KEY (etudiant) REFERENCES utilisateur(mail),
	PRIMARY KEY (offre, etudiant)
);

INSERT INTO utilisateur (mail, mot_de_passe, droit) VALUES ('aliabir_35@hotmail.com', '18011990', 'Entreprise');
/*
INSERT INTO offre_stage (entreprise, domaine, libelle, date_debu, duree, chemin_stockage, description)
VALUES ()
*/
INSERT INTO domaine VALUES ("Réseau"), ("Système"), ("Développement Java");

INSERT INTO candidature VALUES (2, 'aliabir_35@hotmail.com', '', '', TRUE);
INSERT INTO candidature VALUES (3, 'aliabir_35@hotmail.com', '', '', FALSE);
INSERT INTO candidature VALUES (4, 'aliabir_35@hotmail.com', '', '', NULL);
