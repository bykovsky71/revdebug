package com.revdebug.grpc.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Łukasz Rejment
 */

public class DescriptionLoader {

	private static final String DESCRIPTION_HEADER = "name=\"description\"";
	private static final String META_HEADER = "<meta";
	private static final String CONTENT_HEADER = "content=\"";
	private static final int CONTENT_HEADER_SIZE = 9;

	public static String getDescription(String uri) {
		String description = "";
		String urlContent = getURLContent(uri);
		String[] urlContentSplitted = urlContent.split(META_HEADER);
		for (int i = 0; i < urlContentSplitted.length; i++) {
			if (urlContentSplitted[i].contains(DESCRIPTION_HEADER)) {
				int contentIndex = urlContentSplitted[i].indexOf(CONTENT_HEADER) + CONTENT_HEADER_SIZE;
				String content = urlContentSplitted[i].substring(contentIndex);
				description = content.substring(0, urlContentSplitted[i].substring(contentIndex + CONTENT_HEADER_SIZE).indexOf("\"") +
						CONTENT_HEADER_SIZE
				);
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
