package client.reader;

import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.UpdateData;

import java.io.IOException;

/**
 * Interface of command reader
 */

public interface CommandReader {
    Ticket readTicket() throws IOException;

    void executeNext();

    Response getResponse(String command);

    TicketBuilder getBuilder();

    UpdateData getUpdateData();
}
