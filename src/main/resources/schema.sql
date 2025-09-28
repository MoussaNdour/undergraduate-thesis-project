-- ==============================================================
-- SCRIPT DE CRÉATION DE LA BASE DE DONNÉES - VERSION CORRIGÉE
-- ==============================================================

-- ==============================================================
-- PHASE 1 : SUPPRESSION DES TABLES (ordre inverse de création)
-- ==============================================================

-- Supprimer les tables de liaison en premier
DROP TABLE IF EXISTS proposer;
DROP TABLE IF EXISTS Evaluation;

-- Supprimer les tables avec clés étrangères
DROP TABLE IF EXISTS DemandeService;
DROP TABLE IF EXISTS Administrateur;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Prestataire;

-- Supprimer les tables de base
DROP TABLE IF EXISTS Service;
DROP TABLE IF EXISTS Contrat;
DROP TABLE IF EXISTS Paiement;
DROP TABLE IF EXISTS Localisation;
DROP TABLE IF EXISTS Utilisateur;

-- ==============================================================
-- PHASE 2 : CRÉATION DES TABLES (ordre logique de dépendance)
-- ==============================================================

-- Table de base pour tous les types d'utilisateurs
CREATE TABLE Utilisateur (
   idutilisateur        SERIAL PRIMARY KEY,        -- Auto-incrémentation
   nom                  VARCHAR(100) NOT NULL,     -- Nom de l'utilisateur
   prenom               VARCHAR(100) NOT NULL,     -- Prénom de l'utilisateur
   telephone            VARCHAR(20) NOT NULL,      -- Format international
   email                VARCHAR(254) UNIQUE NOT NULL, -- Email unique
   adresse              VARCHAR(200) NOT NULL,     -- Adresse postale
   motDePasse           VARCHAR(255) NOT NULL,     -- Mot de passe haché
   date_inscription     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date d'inscription
   type_utilisateur     VARCHAR(20) DEFAULT 'client' -- Rôle: client, prestataire, admin
);

-- Table des coordonnées géographiques
CREATE TABLE Localisation (
   idlocal              SERIAL PRIMARY KEY,
   latitude             DECIMAL(10, 8) NOT NULL,   -- Latitude précise
   longitude            DECIMAL(11, 8) NOT NULL,   -- Longitude précise
   adresse_complete     VARCHAR(200),              -- Adresse lisible
   ville                VARCHAR(100),              -- Ville
   code_postal          VARCHAR(20),               -- Code postal
   date_creation        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Catalogue des services proposés
CREATE TABLE Service (
   idservice            SERIAL PRIMARY KEY,
   nom                  VARCHAR(100) NOT NULL,     -- Nom du service
   description          TEXT,                      -- Description détaillée
   categorie            VARCHAR(100) NOT NULL,     -- Catégorie du service
   tarif_horaire_min    DECIMAL(10,2),            -- Tarif minimum
   tarif_horaire_max    DECIMAL(10,2),            -- Tarif maximum
   duree_moyenne        INT,                      -- Durée moyenne en minutes
   actif                BOOLEAN DEFAULT TRUE,     -- Service actif ou non
   date_creation        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Gestion des transactions financières
CREATE TABLE Paiement (
   idpaiement           SERIAL PRIMARY KEY,
   montant              DECIMAL(10,2) NOT NULL CHECK (montant > 0), -- Montant positif
   methodePaiement      VARCHAR(50) NOT NULL,     -- Méthode de paiement
   datePaiement         TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date du paiement
   statutPaiement       VARCHAR(20) DEFAULT 'en_attente', -- Statut du paiement
   reference_transaction VARCHAR(100),            -- Référence transaction
   date_maj             TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Date mise à jour
);

-- Contrats de service entre clients et prestataires
CREATE TABLE Contrat (
   idContrat            SERIAL PRIMARY KEY,
   horaires             VARCHAR(100),             -- Horaires de travail
   jours_travail        VARCHAR(100),             -- Jours de travail
   montant_salaire      DECIMAL(10,2) CHECK (montant_salaire >= 0), -- Salaire
   duree_jours          INT CHECK (duree_jours > 0), -- Durée en jours
   type_contrat         VARCHAR(50) DEFAULT 'ponctuel', -- Type de contrat
   date_debut           DATE,                     -- Date de début
   date_fin             DATE,                     -- Date de fin
   termes_contrat       TEXT,                     -- Termes du contrat
   statut               VARCHAR(20) DEFAULT 'actif', -- Statut du contrat
   date_creation        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Administrateurs du système (hérite de Utilisateur)
CREATE TABLE Administrateur (
   idadmin              SERIAL PRIMARY KEY,
   idutilisateur        INT UNIQUE NOT NULL,      -- Lien vers Utilisateur
   codeDAccess          VARCHAR(100),             -- Code d'accès admin
   niveau_acces         INT DEFAULT 1,            -- Niveau de permission
   date_nomination      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de nomination
   FOREIGN KEY (idutilisateur) REFERENCES Utilisateur(idutilisateur) ON DELETE CASCADE
);

-- Prestataires de services (hérite de Utilisateur)
CREATE TABLE Prestataire (
   idprestataire        SERIAL PRIMARY KEY,
   idutilisateur        INT UNIQUE NOT NULL,      -- Lien vers Utilisateur
   idlocal              INT NOT NULL,             -- Localisation principale
   disponibilite        VARCHAR(20) DEFAULT 'disponible', -- Statut disponibilité
   description          TEXT,                     -- Description du prestataire
   competences          TEXT,                     -- Compétences spécifiques
   experience_annees    INT DEFAULT 0,            -- Années d'expérience
   notation_moyenne     DECIMAL(3,2) DEFAULT 0,   -- Note moyenne sur 5
   nombre_avis          INT DEFAULT 0,            -- Nombre total d'avis
   actif                BOOLEAN DEFAULT TRUE,     -- Prestataire actif ou non
   date_creation        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (idutilisateur) REFERENCES Utilisateur(idutilisateur) ON DELETE CASCADE,
   FOREIGN KEY (idlocal) REFERENCES Localisation(idlocal) ON DELETE RESTRICT
);

-- Clients du système (hérite de Utilisateur)
CREATE TABLE Client (
   idclient             SERIAL PRIMARY KEY,
   idutilisateur        INT UNIQUE NOT NULL,      -- Lien vers Utilisateur
   idlocal              INT NOT NULL,             -- Adresse principale
   preferences          TEXT,                     -- Préférences du client
   historique_achats    INT DEFAULT 0,            -- Nombre de services commandés
   fidelite_points      INT DEFAULT 0,            -- Points de fidélité
   date_inscription     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (idutilisateur) REFERENCES Utilisateur(idutilisateur) ON DELETE CASCADE,
   FOREIGN KEY (idlocal) REFERENCES Localisation(idlocal) ON DELETE RESTRICT
);

-- Demandes de service entre clients et prestataires
CREATE TABLE DemandeService (
   iddemande            SERIAL PRIMARY KEY,
   idclient             INT NOT NULL,             -- Client demandeur
   idprestataire        INT NOT NULL,             -- Prestataire assigné
   idservice            INT NOT NULL,             -- Service demandé
   idcontrat            INT,                      -- Contrat associé (optionnel)
   idpaiement           INT,                      -- Paiement associé (optionnel)
   date_de_demande      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date de la demande
   date_de_rendez_vous  TIMESTAMP NOT NULL,       -- Date/heure du service
   statut               VARCHAR(20) DEFAULT 'en_attente', -- Statut de la demande
   details_demande      TEXT,                     -- Détails de la demande
   adresse_service      VARCHAR(200),             -- Adresse spécifique du service
   duree_estimee        INT,                      -- Durée estimée en minutes
   budget_max           DECIMAL(10,2),            -- Budget maximum client
   urgence              VARCHAR(20) DEFAULT 'normal', -- Niveau d'urgence
   date_maj             TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date mise à jour
   FOREIGN KEY (idclient) REFERENCES Client(idclient) ON DELETE CASCADE,
   FOREIGN KEY (idprestataire) REFERENCES Prestataire(idprestataire) ON DELETE CASCADE,
   FOREIGN KEY (idservice) REFERENCES Service(idservice) ON DELETE RESTRICT,
   FOREIGN KEY (idcontrat) REFERENCES Contrat(idcontrat) ON DELETE SET NULL,
   FOREIGN KEY (idpaiement) REFERENCES Paiement(idpaiement) ON DELETE SET NULL
);

-- Évaluations des services rendus
CREATE TABLE Evaluation (
   idevaluation         SERIAL PRIMARY KEY,
   iddemande            INT UNIQUE NOT NULL,      -- Une évaluation par demande
   idclient             INT NOT NULL,             -- Client évaluateur
   idprestataire        INT NOT NULL,             -- Prestataire évalué
   note                 INT NOT NULL CHECK (note >= 1 AND note <= 5), -- Note 1-5
   commentaire          TEXT,                     -- Commentaire détaillé
   date_evaluation      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date évaluation
   reponse_prestataire  TEXT,                     -- Réponse du prestataire
   publique             BOOLEAN DEFAULT TRUE,     -- Évaluation publique ou privée
   FOREIGN KEY (iddemande) REFERENCES DemandeService(iddemande) ON DELETE CASCADE,
   FOREIGN KEY (idclient) REFERENCES Client(idclient) ON DELETE CASCADE,
   FOREIGN KEY (idprestataire) REFERENCES Prestataire(idprestataire) ON DELETE CASCADE
);

-- Table de liaison : Services proposés par les prestataires
CREATE TABLE proposer (
   idproposition        SERIAL PRIMARY KEY,       -- Clé primaire autonome
   idprestataire        INT NOT NULL,             -- Prestataire
   idservice            INT NOT NULL,             -- Service proposé
   tarif_horaire        DECIMAL(10,2) NOT NULL,   -- Tarif spécifique du prestataire
   experience_service   INT DEFAULT 0,            -- Expérience sur ce service
   description_perso    TEXT,                     -- Description personnalisée
   actif                BOOLEAN DEFAULT TRUE,     -- Service activé ou non
   date_ajout           TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date d'ajout
   UNIQUE (idprestataire, idservice),             -- Un service unique par prestataire
   FOREIGN KEY (idprestataire) REFERENCES Prestataire(idprestataire) ON DELETE CASCADE,
   FOREIGN KEY (idservice) REFERENCES Service(idservice) ON DELETE CASCADE
);

-- ==============================================================
-- PHASE 3 : CRÉATION DES INDEXES POUR LES PERFORMANCES
-- ==============================================================

-- Index pour la table Utilisateur (recherches fréquentes)
CREATE INDEX idx_utilisateur_email ON Utilisateur(email);
CREATE INDEX idx_utilisateur_type ON Utilisateur(type_utilisateur);

-- Index pour la table DemandeService (requêtes fréquentes)
CREATE INDEX idx_demande_statut ON DemandeService(statut);
CREATE INDEX idx_demande_date_rendezvous ON DemandeService(date_de_rendez_vous);
CREATE INDEX idx_demande_client ON DemandeService(idclient);
CREATE INDEX idx_demande_prestataire ON DemandeService(idprestataire);

-- Index pour la table Evaluation
CREATE INDEX idx_evaluation_note ON Evaluation(note);
CREATE INDEX idx_evaluation_prestataire ON Evaluation(idprestataire);

-- Index pour la table Service
CREATE INDEX idx_service_categorie ON Service(categorie);
CREATE INDEX idx_service_actif ON Service(actif);

-- Index pour la table Prestataire
CREATE INDEX idx_prestataire_disponibilite ON Prestataire(disponibilite);
CREATE INDEX idx_prestataire_notation ON Prestataire(notation_moyenne);

-- Index pour la table proposer
CREATE INDEX idx_proposer_actif ON proposer(actif);
CREATE INDEX idx_proposer_tarif ON proposer(tarif_horaire);

-- ==============================================================
-- PHASE 4 : CONTRAINTES ET VÉRIFICATIONS SUPPLÉMENTAIRES
-- ==============================================================

-- Vérification que la date de fin est après la date de début dans Contrat
ALTER TABLE Contrat ADD CONSTRAINT chk_contrat_dates 
CHECK (date_fin IS NULL OR date_fin >= date_debut);

-- Vérification des valeurs valides pour la disponibilité des prestataires
ALTER TABLE Prestataire ADD CONSTRAINT chk_disponibilite 
CHECK (disponibilite IN ('disponible', 'occupé', 'absent', 'en_congé'));

-- Vérification des statuts valides pour les demandes de service
ALTER TABLE DemandeService ADD CONSTRAINT chk_statut_demande 
CHECK (statut IN ('en_attente', 'accepté', 'refusé', 'en_cours', 'terminé', 'annulé'));

-- Vérification du statut de paiement
ALTER TABLE Paiement ADD CONSTRAINT chk_statut_paiement 
CHECK (statutPaiement IN ('en_attente', 'payé', 'échoué', 'remboursé', 'en_traitement'));

-- ==============================================================
-- PHASE 5 : VÉRIFICATION DE LA COHÉRENCE DU SCHÉMA
-- ==============================================================

-- Vérification que les dates de rendez-vous sont dans le futur
ALTER TABLE DemandeService ADD CONSTRAINT chk_date_rendezvous_futur 
CHECK (date_de_rendez_vous > date_de_demande);

-- Vérification que la note est entre 1 et 5
ALTER TABLE Evaluation ADD CONSTRAINT chk_note_valide 
CHECK (note >= 1 AND note <= 5);

-- ==============================================================
-- RÉSUMÉ DES CORRECTIONS APPLIQUÉES :
-- ==============================================================
-- ✅ 1. Élimination des dépendances circulaires
-- ✅ 2. Ordre logique de création des tables
-- ✅ 3. Utilisation de SERIAL pour les clés auto-incrémentées
-- ✅ 4. Normalisation des relations (clés simples au lieu de composites)
-- ✅ 5. Amélioration des types de données
-- ✅ 6. Ajout de contraintes CHECK pour la validation
-- ✅ 7. Ajout de colonnes de tracking (dates)
-- ✅ 8. Création d'indexes pour les performances
-- ✅ 9. Commentaires SQL avec -- pour chaque colonne
-- ✅ 10. Gestion appropriée des contraintes d'intégrité

-- ==============================================================
-- FIN DU SCRIPT DE CRÉATION
-- ==============================================================