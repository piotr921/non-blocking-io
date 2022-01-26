package server;

import handler.LoggingHandler;
import handler.ThreadHandler;
import handler.TransmorgifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8111);
        final var handler = new ThreadHandler<>(new LoggingHandler<>(new TransmorgifyHandler()));

        while (true) {
            Socket socket = serverSocket.accept();
            handler.handle(socket);
        }
    }
}
