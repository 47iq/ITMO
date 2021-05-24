package server.connection;

import common.Ticket;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ConnectionManager {
    void readRequest(Socket client) throws ExecutionException, InterruptedException;
}
