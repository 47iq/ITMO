package main.ticket;

import exceptions.*;
import main.AbstractQueueManager;
import org.json.simple.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link DefaultTicket} with {@link JSONObject} parsing methods.
 *  @autor 47iq
 *  @version 1.0
 */

public abstract class ServerDefaultTicket extends DefaultTicket implements ServerTicket {

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
}