package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.UpdateData;
import common.User;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DefaultControllerCommandReader extends AbstractCommandReader {

    private final TicketBuilder builder;

    private final UpdateData updateData;

    public DefaultControllerCommandReader(ConnectionManager commandFactory, ObjectFactory ticketFactory)  {
        // FIXME reader= ;
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        updateData = ticketFactory.getUpdateData();
        builder = ticketFactory.getTicketBuilder();
    }

    @Override
    protected boolean readyForInput() throws IOException {
        return true;
    }

    @Override
    public Ticket readTicket() throws IOException {
        return builder.getResult();
    }

    @Override
    public TicketBuilder getBuilder() {
        return builder;
    }

    @Override
    public UpdateData getUpdateData() {
        return updateData;
    }
}
