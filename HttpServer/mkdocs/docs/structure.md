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
