package fr.ul.miage.reseau.httpserver;

import fr.ul.miage.reseau.httpserver.managers.PropertiesManager;
import fr.ul.miage.reseau.httpserver.server.ServerManager;

public class HttpServer {
	
	public static String PROPERTIES = "";

	public static void main(String[] args) {
		
		boolean argsCorrect = false;
		
		/*
		 * Check if args are correct
		 */
		if(args.length <= 1) {
			System.err.println("Missing argument ! --properties [Location]");
		}else if(args.length >= 3) {
			System.err.println("Too much arguments !");
			System.err.println("Only allowed is --properties [Location]");
		}else {
			if(args[0].equalsIgnoreCase("--properties")) {
				System.out.println("Properties location: " + args[1]);
				PROPERTIES = args[1];
				argsCorrect = true;
			}else {
				System.err.println("Only allowed is --properties [Location]");
			}
		}
		
		if(!argsCorrect) return;
		
		System.out.println("----------[HttpServer - M1 MIAGE]------------");
		System.out.println("Start init managers...");
		initManagers();
		System.out.println("Init managers done.");
	}

	/*
	 * Init instances
	 */
	private static void initManagers() {
		new PropertiesManager(); 
		PropertiesManager.getInstance().initProperties(); //Load properties to memory
		
		new ServerManager();
		ServerManager.getInstance().initServer(); //Init server
	}

}
