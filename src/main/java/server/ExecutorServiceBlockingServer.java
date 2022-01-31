package server;

import handler.ExecutorServiceHandler;
import handler.LoggingHandler;
import handler.ThreadHandler;
import handler.TransmorgifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8111);
        final var handler = new ExecutorServiceHandler<>(
                new LoggingHandler<>(new TransmorgifyHandler()),
                Executors.newCachedThreadPool(),
                (t, e) -> System.out.println("uncaught: " + t + " error: " + e)
        );

        while (true) {
            Socket socket = serverSocket.accept();
            handler.handle(socket);
        }
    }
}
