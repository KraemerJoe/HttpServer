package fr.ul.miage.reseau.httpserver.utils;

import fr.ul.miage.reseau.httpserver.constant.ExtensionsConvertor;

public class WebTypeFileExtension {

	/*
	 * Get extension of the file
	 */
	public static String getExtension(final String path) {
		int slashIndex = path.lastIndexOf("/");
		String basename = (slashIndex < 0) ? path : path.substring(slashIndex + 1);

		int dotIndex = basename.lastIndexOf('.');
		if (dotIndex >= 0) {
			return basename.substring(dotIndex + 1);
		} else {
			return "";
		}
	}

	/*
	 * Get web type from extension
	 */
	public static String getWebtypeOfFile(final String path) {
		String ext = getExtension(path).toLowerCase();
		return ExtensionsConvertor.EXTENSION_CONVERTOR.getOrDefault(ext, "application/octet-stream"); //default is application/octet-stream
	}

}
