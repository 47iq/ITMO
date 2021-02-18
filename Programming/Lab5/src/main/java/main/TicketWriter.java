package main;

import main.ticket.Ticket;

import java.util.Collection;

/**
 * Interface of ticket writer
 */

public interface TicketWriter {

    /**
     * Saves tickets collection
     * @param tickets tickets collection
     */
    void saveTickets(Collection<Ticket> tickets);
}
