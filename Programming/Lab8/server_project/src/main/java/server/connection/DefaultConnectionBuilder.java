package server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;

public class DefaultConnectionBuilder implements ConnectionBuilder {

    private Selector selector;

    private final ServerSocket socket;

    private static volatile boolean isRunning = true;

    public DefaultConnectionBuilder(int port) throws IOException {
        selector = Selector.open();
        socket = new ServerSocket(port);
    }

    @Override
    public Socket accept() throws IOException {
        return socket.accept();
    }

    @Override
    public synchronized void stop() {
        isRunning = false;
    }
}