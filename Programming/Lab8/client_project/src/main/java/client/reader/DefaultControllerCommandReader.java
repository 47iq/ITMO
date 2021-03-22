package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.User;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DefaultControllerCommandReader extends AbstractCommandReader {

    private final TicketBuilder builder;

    public DefaultControllerCommandReader(ConnectionManager commandFactory, ObjectFactory ticketFactory)  {
        // FIXME reader= ;
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        builder = ticketFactory.getTicketBuilder();
    }

    protected boolean readyForInput() throws IOException {
        return true;
    }

    private void resetBuilder() {
        builder.reset();
    }

    public Ticket readTicket() throws IOException {
        return builder.getResult();
    }

    public TicketBuilder getBuilder() {
        return builder;
    }
}
