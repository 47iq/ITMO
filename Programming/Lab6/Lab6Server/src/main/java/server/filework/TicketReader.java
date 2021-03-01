package server.filework;

import server.ticket.ServerTicket;

import java.util.Collection;

/**
 * Interface of ticket reader
 */

public interface TicketReader {
    /**
     * Gets tickets
     * @return ticket collection
     */
    Collection<ServerTicket> getTickets();
}
