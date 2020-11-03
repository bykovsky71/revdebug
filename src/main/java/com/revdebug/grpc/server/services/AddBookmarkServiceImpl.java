package com.revdebug.grpc.server.services;

import com.revdebug.grpc.AddBookmarkServiceGrpc;
import com.revdebug.grpc.AddBookmarkRequest;
import com.revdebug.grpc.AddBookmarkResponse;
import com.revdebug.grpc.server.model.Bookmark;
import com.revdebug.grpc.server.utils.BookmarkSaver;
import com.revdebug.grpc.server.utils.BookmarksLoader;
import com.revdebug.grpc.server.utils.DescriptionLoader;

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
		if (hasValidUri(uri)) {
			if (!BookmarksLoader.uriAlreadyExists(uri)) {
				String tags = formatTags(request);
				String description = DescriptionLoader.getDescription(uri);
				Bookmark bookmark = new Bookmark(uri, tags, description);
				BookmarkSaver.saveBookmark(bookmark);
				sendUriSavedMessage(responseObserver);
			} else {
				sendUriAlreadyExistsMessage(responseObserver);
			}
		} else {
			sendInvalidUriMessage(responseObserver);
		}
	}

	private boolean hasValidUri(String uri) {
		return uri.startsWith("http://") || uri.startsWith("https://");
	}

	private String formatTags(AddBookmarkRequest request) {
		return request.getTags().replace(" ", ", ");
	}

	private void sendInvalidUriMessage(StreamObserver<AddBookmarkResponse> responseObserver) {
		sendMessage(responseObserver, "Invalid uri");
	}

	private void sendUriSavedMessage(StreamObserver<AddBookmarkResponse> responseObserver) {
		sendMessage(responseObserver, "Uri saved");
	}

	private void sendUriAlreadyExistsMessage(StreamObserver<AddBookmarkResponse> responseObserver) {
		sendMessage(responseObserver, "Uri already exists");
	}

	private void sendMessage(StreamObserver<AddBookmarkResponse> responseObserver, String message) {
		AddBookmarkResponse response = AddBookmarkResponse.newBuilder()
				.setResponse(message)
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
