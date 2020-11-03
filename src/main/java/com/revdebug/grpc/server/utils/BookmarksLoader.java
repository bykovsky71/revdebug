package com.revdebug.grpc.server.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Łukasz Rejment
 */

public class BookmarksLoader {

	private static final String BOOKMARKS_FILE = "bookmarks.md";

	public static boolean uriAlreadyExists(String uri) {
		File file = new File(BOOKMARKS_FILE);
		return file.exists() && !file.isDirectory() && containsUri(file, uri);
	}

	private static boolean containsUri(File file, String uri) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> list=new ArrayList<>();
		while(scanner.hasNextLine()){
			list.add(scanner.nextLine());

		}
		scanner.close();
		return list.contains("*url*: " + uri);
	}

}
