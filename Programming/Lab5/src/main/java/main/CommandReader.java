package main;

import main.ticket.Ticket;

import java.io.IOException;

/**
 * Interface of command reader
 */

public interface CommandReader {

    /**
     * Read commands
     */

    void readCommands();

    /**
     * Stop reading commands
     */

    void exit();

    /**
     * Read {@link Ticket}
     * @return ticket
     * @throws IOException in case of reader error
     */

    Ticket readTicket() throws IOException;
}
