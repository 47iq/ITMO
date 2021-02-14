package main;

import exceptions.*;
import org.json.simple.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class which is the realization of {@link AbstractTicket} with {@link JSONObject} parsing methods and {@link String} parsing methods.
 *  @autor 47iq
 *  @version 1.0
 */

public class Ticket extends AbstractTicket {

    /**
     * Counter, which shows number of created elements. Used for generating unique ID values for different {@link Ticket}
     */

    private static int ticketCounter = 0;

    /**
     * Constructor for getting {@link Ticket}, which is used to parse JSON ticket data into the Java code
     * @param jsonTicket {@link JSONObject} must contain JSON data of the ticket
     */

    public Ticket(JSONObject jsonTicket) {
        configureId(++ticketCounter);
        setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        manageName(jsonTicket.get("name"));
        manageCoordinates(jsonTicket.get("coordinates"));
        setPrice(castPrice((String) jsonTicket.get("price")));
        setDiscount(castDiscount((String) jsonTicket.get("discount")));
        manageRefundable(jsonTicket.get("refundable"));
        manageType(jsonTicket.get("type"));
        managePerson(jsonTicket.get("person"));
    }

    /**
     * Constructor for getting {@link Ticket} from its fields
     * @param name {@link AbstractTicket#getName()}
     * @param coordinates {@link AbstractTicket#getCoordinates()}
     * @param price {@link AbstractTicket#getPrice()}
     * @param discount {@link AbstractTicket#getDiscount()}
     * @param refundable {@link AbstractTicket#getRefundable()}
     * @param type {@link AbstractTicket#getType()}
     * @param person {@link AbstractTicket#getPerson()}
     */

    public Ticket(String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        configureId(++ticketCounter);
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
     * Method which is being used for transition of jsonName into name field
     * @param jsonName {@link Object} containing data of the name
     */

    private void manageName(Object jsonName) {
        setName((String) jsonName);
        if(jsonName == null || !AbstractTicket.nameValid(getName()))
            throw new exceptions.InvalidNameException();
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
            setType(AbstractTicket.castType((String) jsonType));
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
     * Method which casts String price to int
     * @param price {@link String}
     * @return int price
     */

    private int castPrice(String price){
        int priceInt = Integer.parseInt(price);
        if(!AbstractTicket.priceValid(priceInt))
            throw new InvalidPriceException();
        else
            return priceInt;
    }

    /**
     * Method which casts {@link String} discount to double
     * @param discount {@link String}t
     * @return discount double
     */

    private double castDiscount(String discount){
        double discountDouble = Double.parseDouble(discount);
        if(!AbstractTicket.discountValid(discountDouble)) {
            throw new InvalidDiscountException();
        }
        else
            return discountDouble;
    }

    /**
     * Method which casts {@link String} refundable to {@link Boolean}
     * @param refundable {@link String}
     * @return refundable {@link Boolean}
     */

    private Boolean castRefundable(String refundable){
        return Boolean.parseBoolean(refundable);
    }

    /**
     * Method which casts {@link JSONObject} coordinatesJSON into main.Coordinates
     * @param coordinatesJSON {@link JSONObject}
     * @return coordinates {@link Coordinates}
     */

    private Coordinates castCoordinates(JSONObject coordinatesJSON) {
        if(coordinatesJSON != null)
            return new Coordinates(coordinatesJSON);
        else
            throw new InvalidCoordinatesException();
    }

    /**
     * Method which casts {@link JSONObject} personJSON into main.Person
     * @param personJSON {@link JSONObject}
     * @return person {@link Person}
     */

    private Person castPerson(JSONObject personJSON) {
        if(personJSON != null) {
            return new Person(personJSON);
        }
        else
            throw new InvalidPersonException();
    }
}