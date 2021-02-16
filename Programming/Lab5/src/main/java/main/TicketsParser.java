package main;

import java.util.Collection;

public interface TicketsParser {
    /**
     * Gets tickets from file
     * @return ticket collection
     */
    Collection<Ticket> getTickets();
}
