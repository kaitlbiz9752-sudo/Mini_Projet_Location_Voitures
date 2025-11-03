
# Lien de la vidéo descriptive

https://drive.google.com/drive/folders/1AhTakR9BnM-jgUWkpZ9TMiMy0vKgd5cr?usp=sharing        

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
🔧 Modifications Techniques
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




 

