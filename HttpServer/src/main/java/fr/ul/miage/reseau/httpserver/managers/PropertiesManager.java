package fr.ul.miage.reseau.httpserver.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import fr.ul.miage.reseau.httpserver.HttpServer;

public class PropertiesManager {
	
	public static PropertiesManager instance;
	
	public PropertiesManager() {
		instance = this;
	}

	private Properties appProps = new Properties();

	/*
	 * Init properties
	 */
	public void initProperties() {
		System.out.println("-> Init properties...");
		try {
			File yourFile = new File(HttpServer.PROPERTIES);
			yourFile.createNewFile();
			appProps.load(new FileInputStream(HttpServer.PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkMissingProperties();
		System.out.println("-> Properties initied.");
	}
	
	/*
	 * Get nb threads properties
	 */
	public int getNbThreads() {
		String nbThreads = appProps.getProperty("nbThread");
		if(nbThreads == null) appProps.setProperty("nbThread", "10");
		return tryParseInt(nbThreads, 10, "number of threads");
	}
	
	/*
	 * Get port properties
	 */
	public int getServerPort() {
		String httpServerPort = appProps.getProperty("httpServerPort");
		if(httpServerPort == null) appProps.setProperty("httpServerPort", "80");
		return tryParseInt(httpServerPort, 80, "port");
	}
	
	/*
	 * Get server folder properties
	 */
	public String getServerFolder() {
		String httpServerFolder = appProps.getProperty("httpServerFolder");
		if(httpServerFolder == null) appProps.setProperty("httpServerFolder", "C:\\httpserver\\");
		return httpServerFolder;
	}
	
	/*
	 * Check if all properties are present, else, set to default values
	 */
	private void checkMissingProperties() {
		String httpServerFolder = appProps.getProperty("httpServerFolder");
		if(httpServerFolder == null) appProps.setProperty("httpServerFolder", "C:\\httpserver\\");
		
		String httpServerPort = appProps.getProperty("port");
		if(httpServerPort == null) appProps.setProperty("httpServerPort", "80");
		
		String nbThread = appProps.getProperty("nbThread");
		if(nbThread == null) appProps.setProperty("nbThread", "10");
		
		try {
			appProps.store(new FileWriter(HttpServer.PROPERTIES), "Store to properties file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Try to parse to int, else return default value
	 */
	public int tryParseInt(String value, int defaultVal, String data) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	    	System.err.println("Cannot parse your " + data + ": " + value + " ! Setting " + data + " to " + defaultVal);
	        return defaultVal;
	    }
	}
	
	public static PropertiesManager getInstance() {
		if(instance == null) instance = new PropertiesManager();
		return instance;
	}

	public static void setInstance(PropertiesManager instance) {
		PropertiesManager.instance = instance;
	}


}
