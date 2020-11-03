package com.revdebug.grpc.server.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Łukasz Rejment
 */

public class URLContentLoader {

	public static String getURLContent(String uri) {
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
