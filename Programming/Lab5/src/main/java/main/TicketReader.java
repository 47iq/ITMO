package main;

import main.ticket.Ticket;

import java.util.Collection;

public interface TicketReader {
    /**
     * Gets tickets from file
     * @return manager.ticket collection
     */
    Collection<Ticket> getTickets();
}
