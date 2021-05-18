package fr.ul.miage.reseau.httpserver.server;

public class ServerThreadShutdown extends Thread {

	/*
	 * Turn off the Server
	 */
	
	@Override
	public void run() {
		ServerManager.off();
	}
}
