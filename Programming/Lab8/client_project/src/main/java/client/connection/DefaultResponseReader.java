package client.connection;

import common.DefaultResponse;
import common.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DefaultResponseReader implements ResponseReader {

    private final SocketChannel channel;

    public DefaultResponseReader(SocketChannel socketChannel) {
        this.channel = socketChannel;
    }

    public Response readResponse() throws IOException, ClassNotFoundException {
        Response response = unwrapResponse(readBytes());
        channel.socket().close();
        channel.close();
        return response;
    }

    private DefaultResponse unwrapResponse(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (DefaultResponse) objectInputStream.readObject();
    }

    private byte[] readBytes() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(64000);
        channel.read(buffer);
        buffer.flip();
        return buffer.array();
    }
}
