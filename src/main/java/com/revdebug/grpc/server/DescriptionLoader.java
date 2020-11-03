package com.revdebug.grpc.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Łukasz Rejment
 */

public class DescriptionLoader {

	public static String getDescription(String uri) {
		String description = "";
		String urlContent = getURLContent(uri);
		String[] urlContentSplitted = urlContent.split("<meta content=");
		for (int i = 0; i < urlContentSplitted.length; i++) {
			if (urlContentSplitted[i].contains("name=\"description\"")) {
				description = urlContentSplitted[i].substring(1, urlContentSplitted[i].indexOf("\"", 1));
			}
		}

		return description;
	}

	private static String getURLContent(String uri) {
		URL url = null;
		Scanner sc = null;

		try {
			url = new URL(uri);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			sc = new Scanner(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		while(sc.hasNextLine()) {
			sb.append(sc.nextLine());
		}
		return sb.toString();
	}

}
