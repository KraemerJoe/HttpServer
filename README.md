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

Ce projet a été réalisé par les étudiants KRAEMER Joé, DUPONT Félix et HAMED Aichetou.
Le projet a été encadré par notre professeur J. Bertrand.

## Licence

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

## Structure du projet

Le projet est structuré de la manière suivante :

	src/main/java
		fr.ul.miage.reseau.httpserver
			HttpServer.java            # Main de l'application, cette classe permet de lancer l'application
		    constants			# Ce package contient les paramètres et constantes
		    	ExtensionsConvertor.java		# Cette classe permet de convertir les extensions de nos fichiers vers les types web
		    managers					# Ce package contient toutes les classes permettant le traitement et sauvegarde en mémoire des données
		    	PropertiesManager.java			# Cette classe permet de gérer et sauvegarder nos propriétés
		    server					# Ce package contient toutes les classes relatives au fonctionnement de notre serveur web
		    	ServerManager.java		# Cette classe permet de gérer les threads de notre serveur et de les executer
		    	ServerRessourceManager.java		# Cette classe permet de gérer le traitements des ressources de notre serveur
		    	ServerThreadShutDown.java	# Cette classe permet d'éteindre le thread principale de notre serveur web
		    utils						# Ce package contient tous les utilitaires de notre serveur
		    	AuthCredentials.java			# Cette classe est un pojo login/password simple
		    	MD5Utils.java			# Cette classe contient les algorithmes MD5
		    	WebTypeFileExtension.java			# Cette classe contient les fonctions de conversion d'une extension vers un web type


# Comment lancer l'application ?

# Prérequis

Afin de lancer l'application, il vous faudra Java sur votre Machine.
La version utilisée pour ce projet est Java 11.

Vous pouvez vérifier la version de votre Java en tappant `java -version` dans votre cmd/terminal.

![javav](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/java.png)

Maintenant il vous faut déterminer sur quel OS vous vous trouvez, ensuite suivez l'explication correspondante:


# Sur Microsoft Windows

Commencez par localiser votre fichier `HttpServer-0.0.1-SNAPSHOT-src.zip` puis extrayez-le.
Rendez vous ensuite dans le dossier extrait et finalement dans le dossier `bindist-win`, ensuite "bin" et lancez `run.bat`.

Vous devriez avoir une erreur:
![javav](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/missingArg.png)

Ceci vous indique qu'il vous manque l'argument `--properties {location}` il vous faut donc spécifié via un argument l'endroit où vous souhaitez avoir votre fichier de propriété.

Voici un exemple:

![javav](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/argOk.png)


Le serveur est alors lancé, félicitations.

# Sur un système Unix / Linux / Apple Mac OS

Commencez par localiser votre fichier `HttpServer-0.0.1-SNAPSHOT-src.zip` puis extrayez-le.
Rendez vous ensuite dans le dossier extrait et finalement dans le dossier `bindist-unix`, ensuite "bin" et lancez `run`.

Vous devriez avoir une erreur qui vous dit qu'il manque un argument.
Ceci vous indique qu'il vous manque l'argument `--properties {location}` il vous faut donc spécifié via un argument l'endroit où vous souhaitez avoir votre fichier de propriété.
Il vous faut donc faire `./run --properties {votre localisation}`.

Le serveur est alors lancé, félicitations.

# Erreur commune

Il se peut que vous rencontriez cette erreur:

`[ERROR] During server starting : java.net.BindException: Address already in use: bind`

Cela veut dire que vous avez soit le port du serveur qui est déjà utilisé, soit que vous avez deux instance du programme.

# Guide d'utilisation

## Aperçu

Lorsque le serveur est correctement lancé, vous devriez avoir des informations similaire dans votre console.

![started](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/started.png)

## Fichier properties
 
Lorsque vous lancez le serveur pour la première fois, un fichier de propriété est créer à la location que vous avez définit dans votre argument --properties.
Il est de base remplie avec ces informations:

![properties](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/properties.png)

Description des propriétés:

	1 - Nombre de threads concurrents maximums
	2 - Dossier dans lequel se trouve les fichiers de vos sites
	3 - Port du serveur
	
Une fois le serveur éteins, vous pouvez alors éditer les propriétés et relancer le serveur avec le même paramètre pour l'endroit des propriétés.


## Premier accès au localhost

Lors de votre premier accès, si vous n'avez mis aucun fichier dans le dossier de votre serveur web, vous devriez tombé sur une erreur 404.

![404](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/404.png)

### Détail des requêtes

Lorsque vous accéder à un site, vous aurez dans la console le retour des requêtes.

![req](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/req.png)

Vous pourrez voir :

	- Le protocol
	- La méthode
	- L'ip de la personne
	- La ressource demandée
	- Le type de contenu (type web) demandé

### Remplir le dossier du serveur web

Prenons pour exemple les ressources fournies par le professeur pour ce projet.
En retournant sur localhost:80 nous retrouverons ceci:

#### index.html

![preview1](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/preview1.png)

Nous pouvons également aller dans les deux autres dossiers :

#### Dopetrope

![preview2](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/preview2.png)

#### Verti

![preview3](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/preview3.png)

#Protéger un dossier

## Fichier .htpasswd

Pour protéger un dossier il est possible de rajouter un fichier `.htpasswd` à la racine d'un dossier.

Exemple dans le dossier /verti.
![htpasswd](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/htpasswd.png)

## Identifiants

Pour rajouter des identifiants, ouvrez le fichiers `.htpasswd` du répertoire protégé et rajoutez une ligne de la manière suivante:

	nom_utilisateur:mdp_en_md5

Exemple:

![htpasswdcontent](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/htpasswdcontent.png)


## Accéder au répertoire protégé

Il vous sera alors demandé un des identifiants d'une ligne de votre `.htpasswd` pour vous connecter.
Attention, le mot de passe demandé est celui qui n'est pas en MD5.

![auth](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/auth.png)

#Compression GZIP des ressources

## Fonctionnement du code

Afin de compresser nos ressources, il a fallu compresser les bytes avec un utilitaire GZIP présent dans Java.
![gzipped](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/gzipped.png)

De plus il fallait changer le type des ressources pour que le navigateur comprenne qu'il s'agissait des ressources compréssées.
![gzipped2](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/gzipped_2.png)

## Vérification du fonctionnement

Pour vérifier que nos ressources sont bien compréssées, on peut utiliser l'outil de développement de Google Chrome.
On se rend ensuite dans la liste de nos ressources et on peut voir le type de compression de ces dernières.

Prenons par exemple la ressource pic01.jpg
![gzippedr](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/gzipped_r.png)

On voit alors que le type d'encodage est bien gzip et que le navigateur arrive a bien l'interpréter.
![gzippedz](https://github.com/KraemerJoe/HttpServer/blob/main/HttpServer/mkdocs/site/img/gzipped_z.png)
