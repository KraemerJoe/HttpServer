package fr.ul.miage.reseau.httpserver.constant;

import java.util.HashMap;
import java.util.Map;

public final class ExtensionsConvertor {

	public static final Map<String, String> EXTENSION_CONVERTOR = new HashMap<>();
	
	/*
	 * Convert file extension to web type
	 */
	
	static {
		EXTENSION_CONVERTOR.put("php", "text/plain");
		EXTENSION_CONVERTOR.put("ts", "video/mp2t");
		EXTENSION_CONVERTOR.put("appcache", "text/cache-manifest");
		EXTENSION_CONVERTOR.put("css", "text/css");
		EXTENSION_CONVERTOR.put("asc", "text/plain");
		EXTENSION_CONVERTOR.put("gif", "image/gif");
		EXTENSION_CONVERTOR.put("htm", "text/html");
		EXTENSION_CONVERTOR.put("html", "text/html");
		EXTENSION_CONVERTOR.put("java", "text/x-java-source");
		EXTENSION_CONVERTOR.put("m3u", "audio/mpeg-url");
		EXTENSION_CONVERTOR.put("ogv", "video/ogg");
		EXTENSION_CONVERTOR.put("flv", "video/x-flv");
		EXTENSION_CONVERTOR.put("mov", "video/quicktime");
		EXTENSION_CONVERTOR.put("swf", "application/x-shockwave-flash");
		EXTENSION_CONVERTOR.put("pdf", "application/pdf");
		EXTENSION_CONVERTOR.put("md", "text/plain");
		EXTENSION_CONVERTOR.put("txt", "text/plain");
		EXTENSION_CONVERTOR.put("xlsm", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		EXTENSION_CONVERTOR.put("xml", "application/xml");
		EXTENSION_CONVERTOR.put("zip", "application/zip");
		EXTENSION_CONVERTOR.put("m3u8", "application/vnd.apple.mpegurl");
		EXTENSION_CONVERTOR.put("js", "application/javascript");
		EXTENSION_CONVERTOR.put("json", "application/json");
		EXTENSION_CONVERTOR.put("jpg", "image/jpeg");
		EXTENSION_CONVERTOR.put("jpeg", "image/jpeg");
		EXTENSION_CONVERTOR.put("mp3", "audio/mpeg");
		EXTENSION_CONVERTOR.put("mp4", "video/mp4");
		EXTENSION_CONVERTOR.put("doc", "application/msword");
		EXTENSION_CONVERTOR.put("ogg", "application/x-ogg");
		EXTENSION_CONVERTOR.put("png", "image/png");
		EXTENSION_CONVERTOR.put("svg", "image/svg+xml");
	};

}
