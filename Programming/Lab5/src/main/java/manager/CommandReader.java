package manager;

import ticket.Ticket;

import java.io.IOException;

public interface CommandReader {
    void readCommands();
    void exit();
    Ticket readTicket() throws IOException;
}
