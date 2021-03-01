package client.connection;

import common.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DefaultRequestSender implements RequestSender {

    private final DataOutputStream outputStream;

    public DefaultRequestSender(DataOutputStream outputStream) {
           this.outputStream = outputStream;
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

    public void sendRequest(Request request) throws IOException {
        outputStream.write(wrapRequest(request));
        outputStream.flush();
    }
}
