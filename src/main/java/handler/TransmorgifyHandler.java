package handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmorgifyHandler implements Handler<Socket> {

    @Override
    public void handle(Socket socket) throws IOException {
        try (socket;
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()
        ) {
            int data;
            while ((data = inputStream.read()) != -1) {
                if (data == '%') throw new IOException("OOPS!");
                outputStream.write(transmorgify(data));
            }
        }
    }

    private static int transmorgify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

}
