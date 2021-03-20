package server.datawork;

import server.ticket.ServerTicket;

import java.util.Collection;

/**
 * Interface of ticket writer
 */

public interface TicketWriter {

    /**
     * Saves tickets collection
     * @param tickets tickets collection
     */
    void saveTickets(Collection<ServerTicket> tickets);
}
