package com.revdebug.grpc.client;

import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.revdebug.grpc.AddBookmarkServiceGrpc;
import com.revdebug.grpc.AddBookmarkRequest;
import com.revdebug.grpc.AddBookmarkResponse;

/**
 * Created by Lukasz Rejment
 */

public class GRPCClient {

	private static final String IP_ADDRESS = "localhost";
	private static final int PORT = 8080;
	private static final String EXIT_COMMAND = "exit";
	private static final String INITIAL_MESSAGE = "Enter 'exit' to finish adding bookmarks";
	private static final String ADD_BOOKMARK_COMMAND = "add-bookmark";
	private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Available commands: add-bookmark <URI> <TAGS>";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(INITIAL_MESSAGE);
		while (!input.hasNext(EXIT_COMMAND) && input.hasNextLine()) {
			String userInput = input.nextLine();
			if (userInput.startsWith(ADD_BOOKMARK_COMMAND)) {
				sendRequest(userInput);
			} else {
				System.out.println(INVALID_COMMAND_MESSAGE);
			}
		}
	}

	private static void sendRequest(String userInput) {
		String uri = getURI(userInput);
		String tags = getTags(userInput);

		if (!uri.isEmpty() && !tags.isEmpty()) {
			sendMessage(uri, tags);
		} else {
			System.out.println(INVALID_COMMAND_MESSAGE);
		}
	}

	private static void sendMessage(String uri, String tags) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(IP_ADDRESS, PORT)
				.usePlaintext()
				.build();

		AddBookmarkServiceGrpc.AddBookmarkServiceBlockingStub stub
				= AddBookmarkServiceGrpc.newBlockingStub(channel);

		AddBookmarkResponse addBookmarkResponse = stub.addBookmark(AddBookmarkRequest.newBuilder()
				.setUri(uri)
				.setTags(tags)
				.build());

		System.out.println("Response received from server:\n" + addBookmarkResponse);

		channel.shutdown();
	}

	private static String getURI(String userInput) {
		String[] userInputArray = userInput.split(" ");
		return userInputArray.length > 1 ? userInputArray[1] : "";
	}

	private static String getTags(String userInput) {
		String[] userInputArray = userInput.split(" ");
		String tags;
		if (userInputArray.length > 2) {
			int tagsIndex = userInput.indexOf(" ", userInput.indexOf(" ") + 1);
			tags = userInput.substring(tagsIndex + 1);
		} else {
			tags = "";
		}
		return tags;
	}

}
