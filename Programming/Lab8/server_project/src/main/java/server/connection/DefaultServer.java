package server.connection;

import common.DefaultRequest;
import common.DefaultUser;
import common.Request;
import common.RequestType;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultServer implements Server {

    private final ConnectionManager connectionManager;

    private final ConnectionBuilder connectionBuilder;

    private final int port;

    private static volatile boolean isRunning = true;

    private final ExecutorService readingThreads = Executors.newCachedThreadPool();

    public DefaultServer(ConnectionManager connectionManager, ConnectionBuilder connectionBuilder, int port) {
        this.connectionManager = connectionManager;
        this.connectionBuilder = connectionBuilder;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        LogManager.getLogger(DefaultServer.class.getName()).info("Starting command reading...");
        startConsoleCommandReading();
        while (isRunning) {
            Socket client = connectionBuilder.accept();
            Thread readingThread = new Thread(() -> {
                try {
                    connectionManager.readRequest(client);
                } catch (InterruptedException | ExecutionException e) {
                    LogManager.getLogger().error("Error during thread execution: " + e.getClass());
                }
            });
            readingThreads.submit(readingThread);
        }
    }

    private void startConsoleCommandReading() {
        Thread backgroundReaderThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (isRunning) {
                    System.out.print("\n>> ");
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        String[] lines = line.trim().split("\\s+");
                        try {
                            Request request = new DefaultRequest(RequestType.EXECUTE, lines[0], Locale.US);
                            request.setUser(new DefaultUser("47iq", "test"));
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

    @Override
    public synchronized void exit() {
        try {
            connectionBuilder.stop();
            isRunning = false;
            Thread.sleep(3000);
            System.exit(0);
        } catch (Exception e) {
            LogManager.getLogger().error("Can't exit server.");
        }
    }
}
