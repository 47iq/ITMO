package server.connection;

import common.DefaultRequest;
import common.Request;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class DefaultRequestReader implements RequestReader{

    private Selector selector;
    private ByteBuffer buffer;

    public DefaultRequestReader() { }

    public Request readRequest(Selector selector, ByteBuffer buffer) throws IOException, ClassNotFoundException {
        this.selector = selector;
        this.buffer = buffer;
        return deserializeRequest(readBytes());
    }

    private byte[] readBytes() throws IOException {
        SocketChannel channel = null;
        while (channel == null){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if(key.isReadable()){
                    channel = (SocketChannel)key.channel();
                    channel.read(buffer);
                    buffer.flip();
                    channel.register(selector, SelectionKey.OP_WRITE);
                    break;
                }
            }
        }
        return buffer.array();
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
