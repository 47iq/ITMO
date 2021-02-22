package main.ticket;

import main.AbstractQueueManager;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Realization of {@link ServerDefaultTicket}
 */

public class ServerCollectionTicket extends ServerDefaultTicket {

    /**
     * Default constructor
     */

    public ServerCollectionTicket() {

    }

    /**
     * Constructor for conversion from non server ticket
     * @param ticket non server ticket
     */

    public ServerCollectionTicket(Ticket ticket) {
        setId(AbstractQueueManager.getID());
        setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        setName(ticket.getName());
        setCoordinates(ticket.getCoordinates());
        setPrice(ticket.getPrice());
        setDiscount(ticket.getDiscount());
        setRefundable(ticket.getRefundable());
        setType(ticket.getType());
        setPerson(ticket.getPerson());
    }
}
