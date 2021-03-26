package server.connection;

import common.DefaultRequest;
import common.Request;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class DefaultRequestReader implements RequestReader {

    public DefaultRequestReader() {
    }

    public Request readRequest(Socket client) throws IOException, ClassNotFoundException {
        return deserializeRequest(readBytes(client));
    }

    private byte[] readBytes(Socket client) throws IOException {
        byte[] bytes = new byte[4096];
        client.getInputStream().read(bytes);
        return bytes;
    }

    public Request deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (DefaultRequest) inputStream.readObject();
        } catch (EOFException e) {
            throw new RuntimeException("Error. Server can't deserialize the request.");
        }
    }
}
