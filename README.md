# TP : Sécurisation d'une API REST avec JWT

Ce projet démontre l'implémentation d'une authentification stateless basée sur des tokens JWT (JSON Web Token) dans une application Spring Boot.

## Technologies utilisées:

Java 17

Spring Boot 3.x\

Spring Security 6 (Configuration Stateless)

Spring Data JPA & MySQL

JJWT (Librairie pour la gestion des tokens)

## Lancement du projet: 

Base de données : Assurez-vous que MySQL est démarré. La table et les données de test sont créées automatiquement au démarrage via DataInitializer.

Utilisateur par défaut : admin

Mot de passe : 1234

Exécution : Lancez la classe SpringJwtApiApplication.java.

## Tests API avec Postman:

1. Authentification (Login)
Envoyez une requête POST pour obtenir le jeton d'accès.

Méthode : POST
URL : http://localhost:8083/api/auth/login
Body (JSON) :
{  "username": "admin",  "password": "1234"}
Résultat : Le serveur renvoie un accessToken.

![WhatsApp Image 2026-03-29 at 11 19 32](https://github.com/user-attachments/assets/250b11ae-5ec6-424c-92d5-6ae2562bbe6a)

![WhatsApp Image 2026-03-29 at 11 20 21](https://github.com/user-attachments/assets/fb38c4d7-b63e-488d-91df-742cfe54ed45)


2. Accès Profil Utilisateur (Rôle USER/ADMIN)

Testez l'accès à une ressource protégée en utilisant le token obtenu précédemment.

Méthode : GET
URL : http://localhost:8083/api/user/profile
Authorization : Type Bearer Token + ["accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc3NDc3NzY5NywiZXhwIjoxNzc0NzgxMjk3fQ.6Pqb96qyXp6ZsRNShHuQipl9qr1U6Y_NDrDvVn48mmPJRXvWgQAt_r1firWuBZ22WLUAQnTViTao37WKadimzQ",]

![WhatsApp Image 2026-03-29 at 11 23 11](https://github.com/user-attachments/assets/d076b57f-0b76-47ea-bc4f-2210a3b3e17e)

![WhatsApp Image 2026-03-29 at 11 49 40](https://github.com/user-attachments/assets/70ecda6b-e226-43fa-a4fc-f17237d6a328)


3. Accès Admin (Rôle ADMIN uniquement)
Vérifiez que la gestion des rôles fonctionne correctement.

Méthode : GET
URL : http://localhost:8083/api/admin/dashboard
Authorization : Type Bearer Token + [Même token]

![WhatsApp Image 2026-03-29 at 11 51 39](https://github.com/user-attachments/assets/610b3407-4c95-48d9-9d62-0522096b2361)


## Structure du projet
Le projet suit une architecture standard Spring Boot :

config/ : Configuration de la sécurité (SecurityConfig) et initialisation des données (DataInitializer).
jwt/ : Filtre d'autorisation et utilitaire de génération de tokens (JwtUtil).
entities/ : Les entités User et Role.
services/ : Service CustomUserDetailsService pour charger l'utilisateur.
web/ : Contrôleurs REST (AuthController, TestController).


## Conclusion

La réalisation de ce TP a permis de mettre en œuvre une architecture de sécurité moderne et robuste pour une API REST.
En utilisant le standard **JWT** avec **Spring Security 6**, nous avons réussi à mettre en place un système d'authentification
**stateless**, où le serveur ne conserve aucune session client.

Cette approche offre une meilleure scalabilité pour les applications distribuées, puisque chaque requête transporte sa propre preuve d'identité.
De plus, l'intégration de la gestion des rôles (`USER`, `ADMIN`) a permis de contrôler finement les accès aux différentes ressources de l'application.

Ce projet a ainsi permis de maîtriser les concepts clés de la sécurité web actuelle : chaîne de filtres, signatures numériques et séparation des 
responsabilités entre authentification et autorisation.
