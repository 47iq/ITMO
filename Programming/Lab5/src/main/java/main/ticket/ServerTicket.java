package main.ticket;

/**
 * Ticket interface for server application
 */

public interface ServerTicket extends Ticket{

    /**
     * Set id given as string
     * @param id serverTicket's id string
     */

    void setIdStr(String id);

    /**
     * Set creation date
     * @param date server ticket's creation date
     */

    void setDateStr(String date);

    /**
     * Set id
     * @param id server ticket's id
     */

    void setId(int id);
}
