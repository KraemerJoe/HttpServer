# Projet Réseau M1 MIAGE - HttpServer

Vous trouverez dans cette documentation toutes les informations liées au projet de réseau de M1 MIAGE.

# Présentation & Licences

## Présentation

Dans le cadre de la matière Réseau il nous a été demandé d'écrire en Java un serveur web sachant gérer la requête GET.
Plusieurs points nous ont été demandés et voici une liste de nos choix ainsi de ce qui a été effectué:

- Le projet est publié sur github
- Le projet présente un readme et une documentation (document actuel)
- Le serveur doit est écris en Java
- Ce projet n'utilise aucune librairies, il est fait uniquement à partir de la JRE
- Un dossier peut être protégé si on y trouve un .htpasswd
- Il utilise l'algorithme MD5 pour l'encryption des mots de passe du .htpasswd
- Le serveur sait exploiter la requête GET en HTTP/1.x
- Le serveur fonctionne correctement avec tous les sites demandés
- Le serveur gère proprement les erreurs
- Le serveur a un système de multithreading
- Le code a été documenté et rendu le plus possible capable d'être compris et maintenu
- A chaque appel, les requêtes apparaissent en console
- Un fichier properties externe vous permet de configurer : localisation des dossiers du serveur, numéro du port, nombre de threads

Une fonctionnalité bonus a été implémentée:

- Les ressources sont compréssées en GZIP.

## Auteurs

Ce projet a été réalisé par les étudiants KRAEMER Joé et DUPONT Félix.
Le projet a été encadré par notre professeur J. Bertrand.

## Licence

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)