package fr.ul.miage.reseau.httpserver.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.ul.miage.reseau.httpserver.utils.WebTypeFileExtension;

public class ServerRessourceManager implements HttpHandler {

	private final String root;
	private final Map<String, Resource> resources = new HashMap<>();

	/*
	 * Construct the ressource
	 */
	public ServerRessourceManager(String root) throws IOException {
		this.root = root.endsWith("/") ? root : root + "/";


		File[] files = new File(root).listFiles();
		if (files == null) {
			throw new IllegalStateException("Can't find: " + root);
		}
		for (File f : files) {
			processFile("", f);
		}

	}

	/*
	 * Process the ressource
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		String requestPath = httpExchange.getRequestURI().getPath(); // get requested ressource/path

		/*
		 * Parsing details
		 */
		
//		 String request = requestBuilder.toString();
//        String[] requestsLines = request.split("\r\n");
//        String[] requestLine = requestsLines[0].split(" ");
//        String method = requestLine[0];
//        String path = requestLine[1];
//        String version = requestLine[2];
//        String host = requestsLines[1].split(" ")[1];
//
//        List<String> headers = new ArrayList<>();
//        for (int h = 2; h < requestsLines.length; h++) {
//            String header = requestsLines[h];
//            headers.add(header);
//        }

		String method = httpExchange.getRequestMethod(); // get request method
		String protocol = httpExchange.getProtocol(); // get protocol (http/version)
		InetSocketAddress host = httpExchange.getRemoteAddress(); //get host

		System.out.println(protocol + " " + method + " " + host + " " + requestPath);

		processRessource(httpExchange, requestPath);

	}


	private class Resource {
		public final byte[] content;

		public Resource(byte[] content) {
			this.content = content;
		}
	}

	/*
	 * Process file
	 */
	private void processFile(String path, File file) throws IOException {
		if (!file.isDirectory()) {
			/*
			 * Add all ressources to ressources
			 */
			resources.put(path + file.getName(), new Resource(readResource(new FileInputStream(file))));
		}

		if (file.isDirectory()) {
			/*
			 * Recursive processFile for all files in the directory
			 */
			for (File sub : file.listFiles()) {
				processFile(path + file.getName() + "/", sub);
			}
		}
	}

	/*
	 * Get bytes of the ressource and gzip if enable
	 */
	private byte[] readResource(final InputStream in) throws IOException {
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		/*
		 * use GZIPOutputStream to compress bytes
		 */
		OutputStream gZipOut = new GZIPOutputStream(bytesOut); //before Gzip was new DataOutputStream(bout)
		byte[] bs = new byte[4096];
		int r;
		while ((r = in.read(bs)) >= 0) {
			gZipOut.write(bs, 0, r);
		}
		gZipOut.flush();
		gZipOut.close();
		in.close();
		return bytesOut.toByteArray();
	}

	/*
	 * Process the folder of the requested ressource
	 */
	private void processRessource(HttpExchange httpExchange, String ressource) throws IOException {
		ressource = ressource.substring(1);
		ressource = ressource.replaceAll("//", "/");
		if (ressource.length() == 0) {
			ressource = "index.html"; // set .html if no ressources at the end
		} else if (ressource.endsWith("/"))
			ressource = ressource + "index.html"; // add index.html if ends with /
		processFile(httpExchange, root + ressource);
	}

	/*
	 * Process the file
	 */
	private void processFile(HttpExchange httpExchange, String ressource) throws IOException {
		File file = new File(ressource);
		if (file.exists()) {
			InputStream in = new FileInputStream(ressource);

			Resource res = null;

			res = new Resource(readResource(in));


			/*
			 * Bonus GZIP
			 */
			httpExchange.getResponseHeaders().set("Content-Encoding", "gzip");


			String mimeType = WebTypeFileExtension.getWebtypeOfFile(ressource);
			output(httpExchange, res.content.length, res.content, mimeType);
		} else {
			/*
			 * Handle the 404 Error
			 */
			httpError(httpExchange, 404, "Can't find the requested file or ressource.");
		}
	}

	/*
	 * Send the output
	 */
	private void output(HttpExchange httpExchange, int contentLength, byte[] content, String contentType)
			throws IOException {

		//set the content output
		httpExchange.getResponseHeaders().set("Content-Type", contentType);
		System.out.println("         | Content-Type:" + contentType);
		//set the response header lenght
		httpExchange.sendResponseHeaders(200, contentLength);
		// write the responsebody with the content
		httpExchange.getResponseBody().write(content);
		httpExchange.getResponseBody().close();

	}

	/*
	 * Show http error
	 */
	private void httpError(HttpExchange httpExchange, int respCode, String errDesc) throws IOException {
		String message = "HTTP error " + respCode + ": " + errDesc;
		byte[] messageBytes = message.getBytes("UTF-8");

		httpExchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
		httpExchange.sendResponseHeaders(respCode, messageBytes.length);

		OutputStream os = httpExchange.getResponseBody();
		os.write(messageBytes);
		os.close();
	}

}
