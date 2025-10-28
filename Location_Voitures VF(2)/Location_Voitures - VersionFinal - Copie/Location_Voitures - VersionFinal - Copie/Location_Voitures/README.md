# Location de Voitures - Application Web

## 🚗 Description du Projet

Application web moderne de gestion de location de voitures développée avec **Spring Boot** et **Thymeleaf**. Le système permet de gérer un parc automobile, des clients et des locations avec une interface utilisateur moderne et intuitive.

## ✨ Fonctionnalités Principales

### 🎨 Interface Moderne et Responsive
- **Design moderne** avec dégradés et animations
- **Interface responsive** qui s'adapte à tous les écrans
- **Font Awesome icons** pour une meilleure expérience utilisateur
- **Navigation intuitive** avec menu global

### 📸 Galerie de Voitures pour les Visiteurs
- **Page galerie publique** accessible via `/gallery`
- **Cards modernes** pour afficher les voitures disponibles
- **Support des images** avec placeholder automatique si aucune image
- **Informations détaillées** : marque, segment, prix, description

### 📊 Tableau de Bord Statistiques
- **Page statistiques complète** accessible via `/statistics`
- **Graphiques interactifs** avec Chart.js :
  - Voitures par marque (graphique circulaire)
  - Voitures par segment (graphique en barres)
  - Locations par statut (graphique linéaire)
- **Métriques clés** :
  - Total voitures, clients, locations
  - Voitures disponibles vs louées
  - Revenu total et revenu mensuel

### 🚗 Gestion des Voitures
- **Ajout/Modification** de voitures avec :
  - Image URL (support des images externes)
  - Description détaillée
  - Segment (Compact, SUV, Berline, Luxe, etc.)
  - Prix par jour
  - Statut de disponibilité
- **Liste des voitures** avec :
  - Affichage des miniatures d'images
  - Badges colorés pour le statut
  - Actions (Modifier, Supprimer)

## 🛠️ Technologies Utilisées

- **Backend** :
  - Spring Boot 3.5.7
  - Spring Data JPA
  - MySQL
  - Thymeleaf

- **Frontend** :
  - Bootstrap 5.3.3
  - Font Awesome 6.4.0
  - Chart.js (pour les graphiques)
  - CSS3 avec animations et dégradés

## 📁 Structure du Projet

```
Location_Voitures/
├── src/
│   ├── main/
│   │   ├── java/com/example/Location_Voitures/
│   │   │   ├── controllers/
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── VoitureController.java
│   │   │   │   ├── ClientController.java
│   │   │   │   ├── LocationController.java
│   │   │   │   └── StatisticsController.java (🆕)
│   │   │   ├── entities/
│   │   │   │   ├── Voiture.java (🆕 avec imageUrl et description)
│   │   │   │   ├── Client.java
│   │   │   │   └── Location.java
│   │   │   └── repositories/
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html (🎨 redesigned)
│   │       │   ├── statistics.html (🆕)
│   │       │   ├── fragments/
│   │       │   │   └── _layout.html (🎨 redesigned)
│   │       │   └── voitures/
│   │       │       ├── list.html (🎨 redesigned)
│   │       │       ├── form.html (🎨 redesigned)
│   │       │       └── gallery.html (🆕)
```

## 🚀 Installation et Démarrage

### Prérequis
- Java 21
- Maven
- MySQL 8.0+

### Configuration

1. **Cloner le projet**
```bash
git clone [url-du-projet]
cd Location_Voitures
```

2. **Configurer MySQL**
   - Créer une base de données `location_voitures`
   - Modifier `application.properties` si nécessaire

3. **Lancer l'application**
```bash
mvn spring-boot:run
```

4. **Accéder à l'application**
   - Interface principale : http://localhost:8080/
   - Galerie : http://localhost:8080/gallery
   - Statistiques : http://localhost:8080/statistics

## 📝 Pages Disponibles

- `/` - Page d'accueil avec présentation
- `/gallery` - Galerie publique des voitures disponibles (pour visiteurs)
- `/voitures` - Gestion des voitures (liste)
- `/voitures/add` - Ajouter une voiture
- `/voitures/edit/{id}` - Modifier une voiture
- `/clients` - Gestion des clients
- `/locations` - Gestion des locations
- `/statistics` - Tableau de bord avec statistiques et graphiques

## 🎨 Caractéristiques de Design

### Couleurs Principales
- **Dégradé principal** : #667eea → #764ba2 (bleu-violet)
- **Accents** : variés pour les différentes métriques

### Effets Visuels
- **Animations au survol** (hover) sur les cartes et boutons
- **Ombres portées** pour la profondeur
- **Borders arrondis** (border-radius)
- **Transitions fluides** sur les interactions

### Typographie
- **Police** : Segoe UI, Tahoma, Geneva, Verdana, sans-serif
- **Icônes** : Font Awesome 6.4.0

## 📊 Statistiques Disponibles

La page statistiques affiche :
- Nombre total de voitures
- Nombre total de clients
- Nombre total de locations
- Voitures disponibles
- Voitures louées
- Revenu total
- Revenu mensuel
- Répartition des voitures par marque
- Répartition des voitures par segment
- Distribution des locations par statut

## 🖼️ Images des Voitures

Pour ajouter des images aux voitures, vous pouvez :
1. Utiliser un URL d'image externe (recommandé pour commencer)
2. Les images s'affichent automatiquement dans :
   - La page galerie (`/gallery`)
   - La liste des voitures (`/voitures`)
3. Si aucune image n'est fournie, un placeholder avec icône de voiture est affiché

## 🔮 Améliorations Futures Possibles

- Upload de fichiers images locaux
- Système d'authentification
- Gestion des réservations en ligne
- Notifications par email
- Recherche et filtres avancés
- Export des statistiques en PDF
- Mode sombre/clair

## 👨‍💻 Développement

Projet développé avec :
- Spring Boot 3.5.7
- Java 21
- Bootstrap 5.3.3
- Chart.js pour les visualisations

## 📄 Licence

Ce projet est un projet éducatif de démonstration.

## 🤝 Contribution

Pour contribuer au projet :
1. Fork le projet
2. Créez votre branche (`git checkout -b feature/AmazingFeature`)
3. Commitez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

---

**Date de création** : 2025
**Version** : 2.0 (avec statistiques et galerie)

