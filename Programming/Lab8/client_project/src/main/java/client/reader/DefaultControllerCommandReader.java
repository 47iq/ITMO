package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.ticket.TicketBuilder;
import common.Ticket;
import common.UpdateData;

import java.io.IOException;

public class DefaultControllerCommandReader extends AbstractCommandReader {

    private final TicketBuilder builder;

    private final UpdateData updateData;

    public DefaultControllerCommandReader(ConnectionManager commandFactory, ObjectFactory ticketFactory) {
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
