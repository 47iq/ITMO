package manager;

import ticket.Ticket;

import java.util.Collection;

public interface TicketReader {
    /**
     * Gets tickets from file
     * @return ticket collection
     */
    Collection<Ticket> getTickets();
}
