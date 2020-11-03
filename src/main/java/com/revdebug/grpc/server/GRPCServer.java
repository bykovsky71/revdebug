package com.revdebug.grpc.server;

import java.io.IOException;

import com.revdebug.grpc.common.HostProperties;
import com.revdebug.grpc.server.services.AddBookmarkServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Created by Lukasz Rejment
 */

public class GRPCServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(HostProperties.PORT)
				.addService(new AddBookmarkServiceImpl()).build();

		System.out.println("Starting server...");
		server.start();
		System.out.println("Server started!");
		server.awaitTermination();
	}

}
