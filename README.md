# Real-Time Analytics Platform

**Real-Time Analytics Platform** est une architecture distribuée conçue pour ingérer, traiter et visualiser des événements de télémétrie à grande échelle.

Reposant sur une approche microservices avec **Java / Spring Boot** et un frontend en **Angular**, la plateforme est construite pour être hautement disponible et performante. Elle utilise **Apache Kafka** comme bus de messages pour absorber les pics de trafic (buffering), **ClickHouse** pour le stockage analytique (OLAP) historique, et **Redis** couplé aux **WebSockets** pour fournir des métriques en direct (Live Stats) aux tableaux de bord.

## 🏗️ Architecture

Le projet est divisé en deux parties principales : le **Backend** (Microservices & Data) et le **Frontend** (Interface Utilisateur).

### Flux de données (Pipeline)

1. **Ingestion (`ingestion-service`)** : Reçoit les événements bruts (clics, pages vues, logs) via une API REST et les pousse immédiatement dans un topic Kafka.
2. **Message Broker (`Kafka`)** : Met en file d'attente les événements, garantissant aucune perte de données lors des pics de charge.
3. **Traitement (`analytics-worker`)** : Consomme les événements depuis Kafka, les agrège et les sauvegarde dans **ClickHouse** (pour l'historique) et **Redis** (pour le temps réel).
4. **Distribution (`query-service`)** : Expose les données traitées. Il utilise des WebSockets pour envoyer les statistiques en direct (depuis Redis) vers le tableau de bord.
5. **Interface (`Frontend`)** : Application Angular affichant les graphiques et métriques.

## 🚀 Technologies utilisées

- **Backend** : Java 25, Spring Boot 3.5
- **Frontend** : Angular 22, Nginx
- **Data & Messaging** : Apache Kafka (KRaft), ClickHouse, Redis
- **DevOps** : Docker, Docker Compose

## 🛠️ Comment lancer le projet en local

L'ensemble de la plateforme est "dockerisée" pour un démarrage simple.

### 1. Lancer le Backend et l'infrastructure

Placez-vous dans le dossier `Backend` et lancez les conteneurs :

```bash
cd Backend
docker-compose up -d --build
```

_Ceci va démarrer Kafka, Redis, ClickHouse, Ingestion Service et Query Service._

### 2. Lancer le Frontend

Ouvrez un nouveau terminal, placez-vous dans le dossier `Frontend` et lancez son conteneur :

```bash
cd Frontend
docker-compose up -d --build
```

### 3. Accéder à l'application

- **Interface Web (Angular)** : [http://localhost:4200](http://localhost:4200)
- **Interface Kafka UI** : [http://localhost:8080](http://localhost:8080) (Pour monitorer Kafka)

## 📁 Structure du dépôt

```text
.
├── Backend/
│   ├── analytics-worker/    # Service de traitement (Kafka -> DBs)
│   ├── ingestion-service/   # API de collecte d'événements
│   ├── query-service/       # API de restitution & WebSockets
│   └── docker-compose.yml   # Infrastructure et microservices
└── Frontend/
    ├── src/                 # Code source Angular
    ├── Dockerfile           # Build multi-stage (Node + Nginx)
    └── docker-compose.yml   # Déploiement UI
```
