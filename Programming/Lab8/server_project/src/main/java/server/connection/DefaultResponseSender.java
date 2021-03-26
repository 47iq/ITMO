package server.connection;

import common.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DefaultResponseSender implements ResponseSender {

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
