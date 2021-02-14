package main;

/**
 * Abstract class which manages user-friendly output of {@link AbstractTicket}
 * @autor 47iq
 * @version 1.0
 */

public abstract class TicketMessagesFactory {

    /**
     * Method for getting user-friendly output of {@link AbstractTicket}
     * @param ticket {@link AbstractTicket}
     * @return ticketStr {@link String}
     */

    public static String getTicketMessage(AbstractTicket ticket) {
        return "AbstractTicket data:\n    AbstractTicket id: " + ticket.getId() + "\n    Name: " + ticket.getName() +
                "\n    Coordinates: " + getCoordinatesMessage(ticket.getCoordinates())  + "\n    Created at: " +
                ticket.getCreationDate() + "\n    Price: " + ticket.getPrice() + "\n    Discount: " + ticket.getDiscount() +
                "\n    Refundable: " + ticket.getRefundable() +  "\n    Type: " + ticket.getType() + "\n    Person: " +
                getPersonMessage(ticket.getPerson());
    }

    /**
     * Private method for getting user-friendly output of {@link Coordinates}
     * @param coordinates {@link Coordinates}
     * @return coordinatesStr {@link String}
     */

    private static String getCoordinatesMessage(Coordinates coordinates) {
        return "\n        X: " + coordinates.getX() + "\n        Y: " + coordinates.getY();
    }

    /**
     * Method for getting user-friendly output of {@link Person}
     * @param person {@link Person}
     * @return personStr {@link String}
     */

    private static String getPersonMessage(Person person) {
        return "\n        Weight: " + person.getWeight() + "\n        Eyes color: " + person.getEyeColor() + "\n        " +
                "Hair color: " + person.getHairColor() + "\n        Nationality: " + person.getNationality();
    }
}
