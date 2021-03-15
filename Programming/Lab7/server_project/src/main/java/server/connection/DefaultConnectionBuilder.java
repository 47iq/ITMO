package server.connection;

import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class DefaultConnectionBuilder implements ConnectionBuilder{

    private Selector selector;

    private final ServerSocket socket;

    private static volatile boolean isRunning = true;

    public DefaultConnectionBuilder(int port) throws IOException {
        selector = Selector.open();
        socket = new ServerSocket(port);
    }

    public Socket accept() throws IOException {
        return socket.accept();
    }

    public synchronized void stop() {
        isRunning = false;
    }
}