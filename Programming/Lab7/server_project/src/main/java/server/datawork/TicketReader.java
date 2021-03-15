package server.datawork;

import server.ticket.ServerTicket;

import java.sql.SQLException;
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
