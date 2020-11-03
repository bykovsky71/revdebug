package com.revdebug.grpc.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revdebug.grpc.AddBookmarkServiceGrpc;
import com.revdebug.grpc.AddBookmarkRequest;
import com.revdebug.grpc.AddBookmarkResponse;

import io.grpc.stub.StreamObserver;

/**
 * Created by Lukasz Rejment
 */

public class AddBookmarkServiceImpl extends AddBookmarkServiceGrpc.AddBookmarkServiceImplBase {

	@Override
	public void addBookmark(
			AddBookmarkRequest request, StreamObserver<AddBookmarkResponse> responseObserver) {
		System.out.println("Request received from client:\n" + request);

		String uri = request.getUri();
		if (!BookmarksLoader.uriAlreadyExists(uri)) {
			String tags = request.getTags().replace(" ", ", ");
			String description = DescriptionLoader.getDescription(uri);
			Bookmark bookmark = new Bookmark(uri, tags, description);
			BookmarkSaver.saveBookmark(bookmark);
			sendUriSavedMessage(responseObserver);
		} else {
			sendUriAlreadyExistsMessage(responseObserver);
		}
	}

	private void sendUriSavedMessage(StreamObserver<AddBookmarkResponse> responseObserver) {
		AddBookmarkResponse response = AddBookmarkResponse.newBuilder()
				.setResponse("Uri saved")
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	private void sendUriAlreadyExistsMessage(StreamObserver<AddBookmarkResponse> responseObserver) {
		AddBookmarkResponse response = AddBookmarkResponse.newBuilder()
				.setResponse("Uri already exists")
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
