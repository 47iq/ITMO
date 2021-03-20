package server.connection;

import common.Response;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class DefaultResponseSender implements ResponseSender{

    public void sendResponse(Response response, Socket client) throws IOException {
        sendBytes(wrapResponse(response), client);
    }

    public byte[] wrapResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void sendBytes(byte[] bytes, Socket client) throws IOException {
        OutputStream is = client.getOutputStream();
        is.write(bytes);
        is.flush();
    }
}
