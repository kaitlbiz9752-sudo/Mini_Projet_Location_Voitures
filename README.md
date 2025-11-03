
 # Réalisée par 
 **AITLBIZ Kaoutar**
 # Encadrée par 
 **LACHGAR Mohamed**
 # ENS Marrakech

#  🚗 Améliorations du Système de Location de Voitures


## 🏗️ Structure du projet


<img width="950" height="491" alt="image" src="https://github.com/user-attachments/assets/e18a719f-7d1a-4bcb-9d01-98a2e22dab6d" />

## 🏗️ Architecture du projet

<img width="584" height="577" alt="image" src="https://github.com/user-attachments/assets/e9c6b3cb-7b41-4e94-982a-dca89186b2a1" />




## 1. 🎨 Espace Administrateur
Dashboard Admin (/admin) : Tableau de bord complet avec statistiques en temps réel
Cartes statistiques avec design moderne
Accès rapide aux différentes sections (Voitures, Clients, Locations, Statistiques)
Graphiques visuels avec Chart.js
## 2. 🎯 Interface Client
Galerie de Voitures (/gallery) : Affichage moderne des véhicules disponibles

Design par carte (cards) avec images
Prix et informations visibles
Bouton de réservation
Formulaire de Réservation (/reservation/{id}) :

Interface intuitive pour réserver une voiture
Saisie des informations client (Nom, CIN, Téléphone)
Sélection des dates de début et fin
Calcul automatique du montant total
Page de Confirmation : Confirmation visuelle de la réservation avec détails


## Modèle de données :
<img width="814" height="735" alt="image" src="https://github.com/user-attachments/assets/d170b98c-1ac3-4d72-8a2a-d5cea177ebb8" />


## 🧾 Description du schéma de la base de données « Location de voitures »

La base de données du projet Location de voitures est conçue pour gérer les informations relatives aux clients, aux véhicules et aux opérations de location.
Elle repose sur une structure relationnelle simple mais cohérente, composée de trois entités principales : Client, Voiture et Location.
Chaque entité est liée aux autres de manière à représenter fidèlement le fonctionnement d’un système de location automobile.

**L’entité Client**

L’entité Client regroupe toutes les informations concernant les personnes qui louent des voitures auprès de l’agence.
Chaque client est identifié de manière unique par un identifiant (id).
Elle contient également des données essentielles telles que le CIN, le nom et le numéro de téléphone du client.
Grâce à cette entité, le système peut enregistrer et suivre les différents clients, même lorsqu’ils effectuent plusieurs locations au fil du temps.
En effet, un client peut être associé à plusieurs contrats de location, mais chaque location appartient à un seul client.

**L’entité Voiture**

L’entité Voiture contient toutes les informations relatives au parc automobile disponible à la location.
Chaque véhicule possède un identifiant unique (id), un prix journalier, un état de disponibilité, ainsi qu’une description comprenant la marque, le segment (catégorie), et le numéro d’immatriculation.
Une voiture peut également être accompagnée d’une image illustrant son apparence.
Cette entité permet donc de gérer efficacement les véhicules, de connaître leur statut (disponible ou non) et de calculer le coût d’une location selon la durée du contrat.
Comme pour les clients, une voiture peut être concernée par plusieurs locations, mais chaque location ne concerne qu’un seul véhicule à la fois.

**L’entité Location**

L’entité Location représente l’opération de location proprement dite.
Elle contient des informations telles que la date de début, la date de fin, le montant total à payer, et le statut de la location (par exemple : en cours, terminée, ou annulée).
Cette entité établit un lien entre un client et une voiture, à travers deux clés étrangères (client_id et voiture_id).
Elle constitue donc le cœur relationnel du système, reliant les informations clients et véhicules pour chaque transaction.
Grâce à elle, le système peut retracer l’historique complet des locations, les durées, les montants et les états de chaque opération.

**Relations entre les entités**

La conception de la base de données suit une logique de relations un-à-plusieurs :

Un client peut effectuer plusieurs locations au cours du temps.

Une voiture peut être louée plusieurs fois, mais pas simultanément (en fonction de sa disponibilité).

Chaque location associe un seul client à une seule voiture pour une période donnée.

Ainsi, l’entité Location joue le rôle d’intermédiaire entre Client et Voiture, assurant la cohérence et la traçabilité des opérations.

**Synthèse**

En somme, cette base de données modélise de manière efficace le processus de gestion d’un service de location automobile :
elle permet de gérer les clients, de suivre la disponibilité des voitures, et d’enregistrer toutes les opérations de location de manière détaillée et fiable.
Les relations entre les entités garantissent la cohérence des données, tout en facilitant les requêtes nécessaires à la gestion quotidienne du système.
## 3. 💎 Design & Styles
Bootstrap 5 : Framework moderne et responsive
Font Awesome : Icônes professionnelles
Gradients modernes : Palette de couleurs attrayante
Animations : Effets au survol (hover) pour une meilleure UX
Cards avec ombres : Profondeur visuelle
Responsive Design : Compatible mobile, tablette et desktop
## 4. 📊 Améliorations de l'Interface
Liste des Voitures : Tableau stylisé avec badges de statut
Liste des Clients : Design cohérent avec le reste de l'application
Liste des Locations : Affichage clair des réservations
Statistiques : Graphiques interactifs (pie, bar, line charts)
**🔧 Modifications Techniques**
Controllers
AdminController : Gestion du dashboard administrateur
ReservationController : Gestion des réservations clients
HomeController : Gestion de la page d'accueil
Templates
admin/dashboard.html : Nouveau dashboard admin
reservation/form.html : Formulaire de réservation
reservation/confirmation.html : Confirmation de réservation
Amélioration de tous les templates existants
Repository
ClientRepository : Ajout de findByCin() avec Optional
## 🎯 Navigation
Espace Public
/ : Page d'accueil
/gallery : Galerie des voitures disponibles
/gallery → Réserver : Formulaire de réservation
Espace Administrateur
/admin : Dashboard administrateur
/voitures : Gestion des voitures (CRUD)
/voitures/add : Ajouter une voiture
/voitures/edit/{id} : Modifier une voiture
/clients : Gestion des clients (CRUD)
/clients/new : Ajouter un client
/clients/{id}/edit : Modifier un client
/locations : Liste des locations
/statistics : Statistiques détaillées avec graphiques
## 🎨 Design System
Couleurs Principales
--primary-color: #667eea (Bleu)
--secondary-color: #764ba2 (Violet)
--success-color: #43e97b (Vert)
--danger-color: #f5576c (Rouge)
--info-color: #4facfe (Bleu clair)
Composants
Navbar avec gradient moderne
Cards avec hover effects
Badges de statut colorés
Boutons avec transitions
Footer uniforme
Formulaires stylisés
## 📱 Fonctionnalités
Pour les Clients



✅ Parcourir la galerie de voitures




✅ Voir les détails (marque, segment, prix, description)




✅ Réserver une voiture





✅ Remplir le formulaire de réservation





✅ Recevoir une confirmation




Pour l'Administrateur



✅ Accéder au dashboard avec statistiques




✅ Gérer les voitures (CRUD complet)



✅ Gérer les clients (CRUD complet)



✅ Voir les locations



✅ Consulter les statistiques et graphiques



✅ Voir les revenus totaux et mensuels



## 🚀 Démarrage
Démarrer l'application :

./mvnw spring-boot:run
Accéder à l'application :

URL publique : http://localhost:8080/
Galerie : http://localhost:8080/gallery
Espace Admin : http://localhost:8080/admin
Tester la réservation :

Aller sur /gallery
Cliquer sur "Réserver Maintenant" sur une voiture
Remplir le formulaire
Confirmer
## 🎓 Améliorations Futures Possibles
 Authentification (login admin/client)
 Upload d'images pour les voitures
 Système de paiement
 Notifications par email
 Export PDF des contrats
 Recherche et filtres avancés
 Gestion des modèles de véhicules
 Calendrier de disponibilité
 Suivi GPS (optionnel)
 API REST pour mobile
## 📝 Notes Techniques
Framework : Spring Boot 3.5.7
Base de données : MySQL
Templates : Thymeleaf
Frontend : Bootstrap 5 + Font Awesome + Chart.js
Validation : Java Bean Validation


# Démonstration

https://drive.google.com/drive/folders/1AhTakR9BnM-jgUWkpZ9TMiMy0vKgd5cr?usp=sharing   




 

