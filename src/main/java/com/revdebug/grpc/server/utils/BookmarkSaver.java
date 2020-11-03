package com.revdebug.grpc.server.utils;

import java.io.*;

import com.revdebug.grpc.server.model.Bookmark;

/**
 * Created by Łukasz Rejment
 */

public class BookmarkSaver {

	private static final String BOOKMARKS_FILE = "bookmarks.md";

	public static void saveBookmark(Bookmark bookmark) {
		try {
			File file = new File(BOOKMARKS_FILE);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(BOOKMARKS_FILE, true)));
			out.println("*title*: " + bookmark.getTitle());
			out.println("*url*: " + bookmark.getUri());
			out.println("*tags*: " + bookmark.getTags());
			out.println("*description*: " + bookmark.getDescription());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
