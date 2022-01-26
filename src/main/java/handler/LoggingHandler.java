package handler;

import java.io.IOException;

public class LoggingHandler<S> implements Handler<S> {
    private final Handler<S> other;

    public LoggingHandler(Handler<S> other) {
        this.other = other;
    }

    @Override
    public void handle(S s) throws IOException {
        System.out.println("Connected with server: " + s);
        try {
            other.handle(s);
        } finally {
            System.out.println("Disconnected from server: " + s);
        }
    }
}
