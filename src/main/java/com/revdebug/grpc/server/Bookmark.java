package com.revdebug.grpc.server;

/**
 * Created by Łukasz Rejment
 */

public class Bookmark {

	String uri;
	String tags;
	String description;

	public Bookmark(String uri, String tags, String description) {
		this.uri = uri;
		this.tags = tags;
		this.description = description;
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
