package com.revdebug.grpc.server;

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

		String serverResponse = new StringBuilder().append("Hello, ")
				.append(request.getUri())
				.append(" ")
				.append(request.getTags())
				.toString();

		AddBookmarkResponse response = AddBookmarkResponse.newBuilder()
				.setResponse(serverResponse)
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

}
