package server.connection;

import java.net.Socket;
import java.nio.channels.Selector;
import java.util.concurrent.ExecutionException;

public interface ConnectionManager {
    void readRequest(Socket client) throws ExecutionException, InterruptedException;
}
