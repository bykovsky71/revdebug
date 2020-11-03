package com.revdebug.grpc.server.model;

/**
 * Created by Łukasz Rejment
 */

public class Bookmark {

	private String title;
	private String uri;
	private String tags;
	private String description;

	public Bookmark(String title, String uri, String tags, String description) {
		this.title = title;
		this.uri = uri;
		this.tags = tags;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
