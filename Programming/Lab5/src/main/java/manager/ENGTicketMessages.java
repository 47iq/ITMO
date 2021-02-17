package manager;

import ticket.Coordinates;
import ticket.Person;
import ticket.Ticket;

/**
 * Abstract class which manages user-friendly output of {@link Ticket}
 * @autor 47iq
 * @version 1.0
 */

public class ENGTicketMessages extends AbstractTicketMessages {

    public ENGTicketMessages() {
        setTranslations();
    }

    protected void setTranslations() {
        translations.put("id", "ID");
        translations.put("name", "Name");
        translations.put("x", "X coordinate");
        translations.put("y", "Y coordinate");
        translations.put("creation_date", "Creation time");
        translations.put("price", "Price");
        translations.put("discount", "Discount");
        translations.put("refundable", "Refundable");
        translations.put("type", "Ticket type");
        translations.put("weight", "Person's weight");
        translations.put("eye_color", "Person's eye color");
        translations.put("hair_color", "Person's hair color");
        translations.put("nationality", "Person's nationality");
    }
}
