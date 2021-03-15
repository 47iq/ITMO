package client.reader;

import common.Ticket;
import common.User;

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
     * Read {@link Ticket}
     * @return ticket
     * @throws IOException in case of reader error
     */

    Ticket readTicket() throws IOException;

    User readUser() throws IOException;
}
