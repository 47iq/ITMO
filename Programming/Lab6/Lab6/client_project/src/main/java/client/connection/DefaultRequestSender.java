package client.connection;

import common.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DefaultRequestSender implements RequestSender {

    private final SocketChannel channel;

    public DefaultRequestSender(SocketChannel socketChannel) {
           this.channel = socketChannel;
    }

    public byte[] wrapRequest(Request request) throws IOException {
        if(request.getTicket() != null)
            request.setTicket(wrapTicket(request.getTicket()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private Ticket wrapTicket(Ticket ticket) {
        ticket.setCoordinates(DefaultCoordinates.convert(ticket.getCoordinates()));
        ticket.setPerson(DefaultPerson.convert(ticket.getPerson()));
        return DefaultTicket.convert(ticket);
    }

    private void sendBytes(byte[] bytes) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    public void sendRequest(Request request) throws IOException {
        sendBytes(wrapRequest(request));
    }
}
