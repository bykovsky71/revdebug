package com.revdebug.grpc.server.utils;

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
		String urlContent = URLContentLoader.getURLContent(uri);
		String[] urlContentSplitted = urlContent.split(META_HEADER);
		for (int i = 0; i < urlContentSplitted.length; i++) {
			if (urlContentSplitted[i].contains(DESCRIPTION_HEADER)) {
				int contentIndex = urlContentSplitted[i].indexOf(CONTENT_HEADER) + CONTENT_HEADER_SIZE;
				String content = urlContentSplitted[i].substring(contentIndex);
				description = content.substring(0, content.indexOf("\""));
			}
		}

		return description;
	}

}
