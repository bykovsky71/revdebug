package com.revdebug.grpc.server.utils;

/**
 * Created by Łukasz Rejment
 */

public class TitleLoader {

	private static final String TITLE_HEADER = "<title>";
	private static final String TITLE_END_HEADER = "</title>";
	private static final int TITLE_HEADER_LENGTH = 7;

	public static String getTitle(String uri) {
		String urlContent = URLContentLoader.getURLContent(uri);
		return urlContent.substring(urlContent.indexOf(TITLE_HEADER) + TITLE_HEADER_LENGTH, urlContent.indexOf(TITLE_END_HEADER));
	}

}
