package com.revdebug.grpc.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Created by Lukasz Rejment
 */

public class GRPCServer {

	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(PORT)
				.addService(new AddBookmarkServiceImpl()).build();

		System.out.println("Starting server...");
		server.start();
		System.out.println("Server started!");
		server.awaitTermination();
	}

}
