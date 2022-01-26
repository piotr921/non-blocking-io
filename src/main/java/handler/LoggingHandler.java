package handler;

import java.io.IOException;

public class LoggingHandler<S> extends DecoratedHandler<S> {

    public LoggingHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) throws IOException {
        System.out.println("Connected with server: " + s);
        try {
            super.handle(s);
        } finally {
            System.out.println("Disconnected from server: " + s);
        }
    }
}
