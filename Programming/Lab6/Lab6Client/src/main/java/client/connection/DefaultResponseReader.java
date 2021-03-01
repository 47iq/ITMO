package client.connection;

import common.DefaultResponse;
import common.Response;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DefaultResponseReader implements  ResponseReader{

    DataInputStream inputStream;

    public DefaultResponseReader(DataInputStream inputStream) {
         this.inputStream = inputStream;
    }

    public Response readResponse() throws IOException, ClassNotFoundException {
        while (inputStream.available() == 0) {
            //skip
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return unwrapResponse(bytes);
    }

    private DefaultResponse unwrapResponse(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (DefaultResponse) objectInputStream.readObject();
    }
}
