package server.ticket;

import common.Ticket;
import org.json.simple.JSONObject;

/**
 * Ticket interface for server application
 */

public interface ServerTicket extends Ticket {

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

    JSONObject toJSON();

    static ServerTicket copyFromTicket(ServerTicket serverTicket, Ticket ticket) {
        serverTicket.setName(ticket.getName());
        serverTicket.setCoordinates(ticket.getCoordinates());
        serverTicket.setPrice(ticket.getPrice());
        serverTicket.setDiscount(ticket.getDiscount());
        serverTicket.setRefundable(ticket.getRefundable());
        serverTicket.setType(ticket.getType());
        serverTicket.setPerson(ticket.getPerson());
        return serverTicket;
    }
}
