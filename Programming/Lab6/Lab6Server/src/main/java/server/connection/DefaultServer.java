package server.connection;

import common.DefaultRequest;
import common.Request;
import common.RequestType;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Locale;

public class DefaultServer implements Server {

    private final ConnectionManager connectionWorker;

    private final ConnectionBuilder connectionBuilder;

    private final int port;

    public DefaultServer(ConnectionManager connectionWorker, ConnectionBuilder connectionBuilder, int port) {
        this.connectionWorker = connectionWorker;
        this.connectionBuilder = connectionBuilder;
        this.port = port;
    }

    public void readCommands() throws IOException {
        LogManager.getLogger(DefaultServer.class.getName()).info("Starting command reading...");
        startConsoleCommandReading();
        while (true) {
            Selector selector = connectionBuilder.accept();
            connectionWorker.manageConnection(selector);
        }
    }

    private void startConsoleCommandReading() {
        Thread backgroundReaderThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.interrupted()) {
                    System.out.print("\n>> ");
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    else {
                        String[] lines = line.trim().split("\\s+");
                        try {
                            Request request = new DefaultRequest(RequestType.EXECUTE, lines[0], Locale.US);
                            request.setPassword(System.getenv("PASSWORD"));
                            sendServerRequest(request);
                        } catch (Exception e) {
                            LogManager.getLogger().error("Error. Can't execute server command.");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }

    private void sendServerRequest(Request request) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", port));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
            socketChannel.close();
        } catch (Exception e) {
            LogManager.getLogger().error("Error: {}. Can't send request to server.", e.getClass());
        }
    }

    public void exit() {
        System.exit(0);
    }
}
