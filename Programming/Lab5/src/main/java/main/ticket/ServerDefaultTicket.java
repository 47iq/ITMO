package main.ticket;

import exceptions.*;
import main.AbstractQueueManager;
import main.CasterOfDefaultTicket;
import main.ServerObjectFactory;
import org.json.simple.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link AbstractTicket} with {@link JSONObject} parsing methods.
 *  @autor 47iq
 *  @version 1.0
 */

public class ServerDefaultTicket extends AbstractTicket {

    public ServerDefaultTicket(Object id, Object time, String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        configureId(manageID(id));
        setCreationDate(manageDateTime(time));
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setDiscount(discount);
        setRefundable(refundable);
        setType(type);
        setPerson(person);
    }

    public ServerDefaultTicket(Ticket ticket) {
        configureId(AbstractQueueManager.getID());
        setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        setName(ticket.getName());
        setCoordinates(ticket.getCoordinates());
        setPrice(ticket.getPrice());
        setDiscount(ticket.getDiscount());
        setRefundable(ticket.getRefundable());
        setType(ticket.getType());
        setPerson(ticket.getPerson());
    }

    /**
     * Method which is being used for transition of creation date into creation date field
     * @param creationDate date of manager.ticket creation
     * @return creation date object
     */

    private ZonedDateTime manageDateTime(Object creationDate) {
        try {
            return ZonedDateTime.parse((String) creationDate);
        } catch (Exception e) {
            return ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        }
    }

    /**
     * Method which is being used for transition of creation id into creation id field
     * @param id id of the manager.ticket
     * @return id object
     */

    private int manageID(Object id) {
        try{
            int idInt = castId((String) id);
            if(!AbstractQueueManager.idExists(idInt)) {
                AbstractQueueManager.addID(idInt);
                return idInt;
            }
            else
                throw new InvalidIdException();
        } catch (Exception e) {
            return AbstractQueueManager.getID();
        }
    }

    private int castId(String inputStr) {
        int id = Integer.parseInt(inputStr);
        if(idValid(id))
            return id;
        else
            throw new InvalidIdException();
    }

    private boolean idValid(int id) {
        return id > 0;
    }
}