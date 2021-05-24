package client.reader;

import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.UpdateData;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Interface of command reader
 */

public interface CommandReader {
    Ticket readTicket() throws IOException;

    void executeNext();

    Response getResponse(String command);

    Collection<Ticket> getTickets() throws ExecutionException, InterruptedException;

    TicketBuilder getBuilder();

    UpdateData getUpdateData();
}
