package main;

import main.ticket.Ticket;

import java.util.Collection;

/**
 * Interface of ticket reader
 */

public interface TicketReader {
    /**
     * Gets tickets
     * @return ticket collection
     */
    Collection<Ticket> getTickets();
}
