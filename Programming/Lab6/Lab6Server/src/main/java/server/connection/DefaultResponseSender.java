package server.connection;

import common.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class DefaultResponseSender implements ResponseSender{

    private Selector selector;

    private SocketChannel channel;

    public void sendResponse(Response response, Selector selector) throws IOException {
        this.selector = selector;
        sendBytes(wrapResponse(response));
        selector.close();
        channel.socket().close();
        channel.close();
    }

    public byte[] wrapResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void sendBytes(byte[] bytes) throws IOException {
        channel = null;
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while (channel == null){
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for (SelectionKey key : selectedKeys) {
                if(key.isWritable()){
                    channel = (SocketChannel)key.channel();
                    while(buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    break;
                }
            }
        }
    }
}
