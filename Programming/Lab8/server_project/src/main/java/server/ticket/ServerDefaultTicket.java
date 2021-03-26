package server.ticket;

import common.DefaultTicket;
import common.Ticket;
import org.json.simple.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link DefaultTicket} with {@link JSONObject} parsing methods.
 *
 * @version 1.0
 * @autor 47iq
 */

public class ServerDefaultTicket extends DefaultTicket implements ServerTicket {

    /**
     * Default constructor
     */

    public ServerDefaultTicket() {

    }

    /**
     * Constructor for conversion from non server ticket
     *
     * @param ticket non server ticket
     */

    private ServerDefaultTicket(Ticket ticket) {
        setOwner(ticket.getOwner());
        setId(-1);
        setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        setName(ticket.getName());
        setCoordinates(ticket.getCoordinates());
        setPrice(ticket.getPrice());
        setDiscount(ticket.getDiscount());
        setRefundable(ticket.getRefundable());
        setType(ticket.getType());
        setPerson(ticket.getPerson());
    }

    public static ServerDefaultTicket convert(Ticket ticket) {
        return new ServerDefaultTicket(ticket);
    }

    /**
     * Method which is being used for transition of creation date into creation date field
     *
     * @param creationDate date of manager.ticket creation
     */

    private ZonedDateTime manageDateTime(String creationDate) {
        try {
            return ZonedDateTime.parse(creationDate);
        } catch (Exception e) {
            return ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        }
    }

    public void setDateStr(String date) {
        setCreationDate(manageDateTime(date));
    }
}