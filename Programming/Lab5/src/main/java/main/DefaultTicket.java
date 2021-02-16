package main;

import exceptions.*;
import org.json.simple.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link AbstractTicket} with {@link JSONObject} parsing methods.
 *  @autor 47iq
 *  @version 1.0
 */

public class DefaultTicket extends AbstractTicket {

    /**
     * Constructor for getting {@link DefaultTicket}, which is used to parse JSON ticket data into the Java code
     * @param jsonTicket {@link JSONObject} must contain JSON data of the ticket
     */

    public DefaultTicket(JSONObject jsonTicket) {
        configureId(manageID(jsonTicket.get("id")));
        setCreationDate(manageDateTime(jsonTicket.get("creationDate")));
        manageName(jsonTicket.get("name"));
        manageCoordinates(jsonTicket.get("coordinates"));
        setPrice(castPrice((String) jsonTicket.get("price")));
        setDiscount(castDiscount((String) jsonTicket.get("discount")));
        manageRefundable(jsonTicket.get("refundable"));
        manageType(jsonTicket.get("type"));
        managePerson(jsonTicket.get("person"));
    }

    /**
     * Constructor for getting {@link DefaultTicket} from its fields
     * @param name {@link AbstractTicket#getName()}
     * @param coordinates {@link AbstractTicket#getCoordinates()}
     * @param price {@link AbstractTicket#getPrice()}
     * @param discount {@link AbstractTicket#getDiscount()}
     * @param refundable {@link AbstractTicket#getRefundable()}
     * @param type {@link AbstractTicket#getType()}
     * @param person {@link AbstractTicket#getPerson()}
     */

    public DefaultTicket(String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        configureId(AbstractQueueManager.getID());
        setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        setName(name);
        setCoordinates(coordinates);
        setPrice(price);
        setDiscount(discount);
        setRefundable(refundable);
        setType(type);
        setPerson(person);
    }

    /**
     * Method which is being used for transition of creation date into creation date field
     * @param creationDate date of ticket creation
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
     * @param id id of the ticket
     * @return id object
     */

    private int manageID(Object id) {
        try{
            int idInt = Integer.parseInt((String) id);
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

    /**
     * Method which is being used for transition of jsonName into name field
     * @param jsonName {@link Object} containing data of the name
     */

    private void manageName(Object jsonName) {
        setName((String) jsonName);
        if(jsonName == null || !nameValid(getName()))
            throw new InvalidNameException();
    }

    /**
     * Method which is being used for transition of jsonCoordinates into coordinates field
     * @param jsonCoordinates {@link Object} containing data of the coordinates
     */

    private void manageCoordinates(Object jsonCoordinates) {
        if(jsonCoordinates == null)
            throw new InvalidCoordinatesException();
        setCoordinates(castCoordinates((JSONObject) jsonCoordinates));
    }

    /**
     * Method which is used for transition of jsonRefundable into refundable field
     * @param jsonRefundable {@link Object} containing data of the refundable
     */

    private void manageRefundable(Object jsonRefundable) {
        if(jsonRefundable == null)
            setRefundable(null);
        else
            setRefundable(castRefundable((String) jsonRefundable));
    }

    /**
     * Method which is used for transition of jsonType into type field
     * @param jsonType {@link Object} containing data of the type
     */

    private void manageType(Object jsonType) {
        if(jsonType == null)
            setType(null);
        else
            setType(castType((String) jsonType));
    }

    /**
     * Method which is used for transition of jsonPerson into person field
     * @param jsonPerson {@link Object} containing data of the person
     */

    private void managePerson(Object jsonPerson) {
        if(jsonPerson == null)
            throw new InvalidPersonException();
        setPerson(castPerson((JSONObject) jsonPerson));
    }

    /**
     * Method which casts {@link JSONObject} coordinatesJSON into main.Coordinates
     * @param coordinatesJSON coordinates in json object format
     * @return coordinates {@link Coordinates}
     */

    private Coordinates castCoordinates(JSONObject coordinatesJSON) {
        if(coordinatesJSON != null)
            return ObjectFactory.getCoordinates(coordinatesJSON);
        else
            throw new InvalidCoordinatesException();
    }

    /**
     * Method which casts {@link JSONObject} personJSON into main.Person
     * @param personJSON person in json object format
     * @return person {@link Person}
     */

    private Person castPerson(JSONObject personJSON) {
        if(personJSON != null) {
            return ObjectFactory.getPerson(personJSON);
        }
        else
            throw new InvalidPersonException();
    }

    public int compareTo(Ticket o) {
        return 0;
    }
}