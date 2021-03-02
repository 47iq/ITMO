package server.ticket;

import common.DefaultTicket;
import common.Ticket;
import exceptions.InvalidIdException;
import org.json.simple.JSONObject;
import server.collection.AbstractQueueManager;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link DefaultTicket} with {@link JSONObject} parsing methods.
 *  @autor 47iq
 *  @version 1.0
 */

public class ServerDefaultTicket extends DefaultTicket implements ServerTicket {

    /**
     * Default constructor
     */

    public ServerDefaultTicket() {

    }

    /**
     * Constructor for conversion from non server ticket
     * @param ticket non server ticket
     */

    private ServerDefaultTicket(Ticket ticket) {
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

    public static ServerDefaultTicket convert(Ticket ticket) {
        return new ServerDefaultTicket(ticket);
    }

    /**
     * Method which is being used for transition of creation date into creation date field
     * @param creationDate date of manager.ticket creation
     */

    private ZonedDateTime manageDateTime(String creationDate) {
        try {
            return ZonedDateTime.parse(creationDate);
        } catch (Exception e) {
            return ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        }
    }

    /**
     * Method which is being used for transition of id into id field
     * @param id id of the manager.ticket
     */

    private int manageID(int id) {
        try{
            if(!AbstractQueueManager.idExists(id)) {
                AbstractQueueManager.addID(id);
                return id;
            }
            else
                throw new InvalidIdException();
        } catch (Exception e) {
            return AbstractQueueManager.getID();
        }
    }

    public int castId(String inputStr) {
        int id = Integer.parseInt(inputStr);
        if(idValid(id))
            return id;
        else
            throw new InvalidIdException();
    }

    private boolean idValid(int id) {
        return id > 0;
    }

    public void setIdStr(String id) {
        setId(castId(id));
    }

    public void setId(int id) {
        super.setId(manageID(id));
    }

    public void setDateStr(String date) {
        setCreationDate(manageDateTime(date));
    }

    public JSONObject toJSON() {
        JSONObject jsonTicket = new JSONObject();
        JSONObject jsonCoordinates = new JSONObject();
        JSONObject jsonPerson = new JSONObject();
        jsonTicket.put("id", Integer.toString(getId()));
        jsonTicket.put("name", getName());
        jsonCoordinates.put("x", Double.toString(getCoordinates().getX()));
        jsonCoordinates.put("y", getCoordinates().getY().toString());
        jsonTicket.put("coordinates",jsonCoordinates);
        jsonTicket.put("creationDate", getCreationDate().toString());
        jsonTicket.put("price", Integer.toString(getPrice()));
        jsonTicket.put("discount", Double.toString(getDiscount()));
        if(getRefundable() == null)
            jsonTicket.put("refundable", "");
        else
            jsonTicket.put("refundable", getRefundable().toString());
        if(getType() == null)
            jsonTicket.put("type", "");
        else
            jsonTicket.put("type", getType().toString());
        jsonPerson.put("weight", getPerson().getWeight().toString());
        jsonPerson.put("eyeColor", getPerson().getEyeColor().toString());
        jsonPerson.put("hairColor", getPerson().getHairColor().toString());
        jsonPerson.put("nationality", getPerson().getNationality().toString());
        jsonTicket.put("person", jsonPerson);
        return jsonTicket;
    }
}