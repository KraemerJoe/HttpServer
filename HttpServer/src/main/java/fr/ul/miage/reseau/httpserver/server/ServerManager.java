package fr.ul.miage.reseau.httpserver.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;

import fr.ul.miage.reseau.httpserver.constant.ExtensionsConvertor;
import fr.ul.miage.reseau.httpserver.managers.PropertiesManager;
import fr.ul.miage.reseau.httpserver.utils.AuthCredentials;
import fr.ul.miage.reseau.httpserver.utils.MD5Utils;

public class ServerManager implements Runnable {

	
	/*
	 * Server instance
	 */
	public static ServerManager instance;

	/*
	 * Http server variables
	 */
	private static ServerManager server;
	private HttpServer httpServer;
	private ExecutorService service;
	
	/*
	 * Value from properties
	 */
	private static String serverHome;
	private static int port;
	private static int nbThread;

	public ServerManager() {
		instance = this;
	}

	/*
	 * Init the server and read properties
	 */
	public void initServer() {
		port = PropertiesManager.getInstance().getServerPort();
		serverHome = PropertiesManager.getInstance().getServerFolder();
		nbThread = PropertiesManager.getInstance().getNbThreads();
		
		/*
		 * Print properties
		 */
		System.out.println("Properties:");
		System.out.println("   > Server Root : " + serverHome);
		System.out.println("   > Server Port : " + port);
		System.out.println("   > Nb of Threads : " + nbThread);
		System.out.println("Properties:");
		
		server = new ServerManager();

		Thread thread = new Thread(server);
		thread.start();

		Runtime.getRuntime().addShutdownHook(new ServerThreadShutdown());

		try {
			thread.join();
		} catch (Exception e) {
		}
	}

	/*
	 * Run function thread of the server
	 */
	@Override
	public void run() {
		try {
			service = Executors.newFixedThreadPool(nbThread);

			httpServer = HttpServer.create(new InetSocketAddress("localhost", port), 0);
			httpServer.setExecutor(service);
			

			HttpContext httpContextWithAuth = httpServer.createContext("/", new ServerRessourceManager(serverHome));

			
			httpContextWithAuth.setAuthenticator(new BasicAuthenticator("get") {

				HttpExchange ex = null;

				/*
				 * System for the .htpasswd authentication
				 */
				@Override
				public Result authenticate(HttpExchange arg0) {

					boolean authRequired = false;
					if (arg0.getRequestURI().getPath().endsWith("/")) {
						File file = new File(serverHome + arg0.getRequestURI().getPath() + ".htpasswd");
						if (file.exists()) {
							authRequired = true;
						}
					}

					ex = arg0;

					if (!authRequired)
						return new Authenticator.Success(new HttpPrincipal("basic", "get"));
					else
						return super.authenticate(arg0);
				}

				/*
				 * Check if entered credentials are ok
				 */
				@Override
				public boolean checkCredentials(String user, String pwd) {
					File file = new File(serverHome + this.ex.getRequestURI().getPath() + ".htpasswd");
					ArrayList<AuthCredentials> credentials = readCredentialFile(file);

					boolean authed = false;
					for (AuthCredentials authCredentials : credentials) {
						if (authCredentials.getUsername().equals(user)
								&& authCredentials.getPassword().equals(MD5Utils.MD5(pwd)))
							authed = true;
					}

					return authed;
				}
			});

			System.out.println("-> Starting server...");

			httpServer.start();

			System.out.println("-> Server started => " + "localhost" + ":" + port);

			synchronized (this) {
				try {
					this.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println("[ERROR] During server starting : " + e);
		}
	}

	/*
	 * Write the response of the call
	 */
	public static void write(HttpExchange httpExchange, String response) throws IOException {
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	/*
	 * Shutdown the server
	 */
	static void off() {
		try {
			System.out.println("Shutting down server...");
			if (server.httpServer != null)
				server.httpServer.stop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized (server) {
			server.notifyAll();
		}
	}

	/*
	 * Read credential file
	 */
	private ArrayList<AuthCredentials> readCredentialFile(File file) {

		ArrayList<AuthCredentials> credentials = new ArrayList<>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				String[] tab = line.split(":");
				credentials.add(new AuthCredentials(tab[0], tab[1]));
			}
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return credentials;
	}

	public static ServerManager getInstance() {
		if (instance == null)
			instance = new ServerManager();
		return instance;
	}

	public static void setInstance(ServerManager instance) {
		ServerManager.instance = instance;
	}

}
