package client.reader;

import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface of command reader
 */

public interface CommandReader {
    Ticket readTicket() throws IOException;
    Response executeNext();
    Response getResponse(String command);
    TicketBuilder getBuilder();
}
