package main;

import java.util.Collection;

public interface TicketWriter {

    /**
     * Saves tickets
     * @param tickets tickets collection
     */
    void saveTickets(Collection<? extends Ticket> tickets);
}
