package main;

/**
 * Abstract class which manages user-friendly output of {@link AbstractTicket}
 * @autor 47iq
 * @version 1.0
 */

public abstract class TicketMessagesFactory {

    /**
     * Method for getting user-friendly output of {@link AbstractTicket}
     * @param ticket ticket we want to be outputted
     * @return ticketStr ticket in string format
     */

    public static String getTicketMessage(AbstractTicket ticket) {
        return "Ticket data:\n    Ticket id: " + ticket.getId() + "\n    Name: " + ticket.getName() +
                "\n    Coordinates: " + getCoordinatesMessage(ticket.getCoordinates())  + "\n    Created at: " +
                ticket.getCreationDate() + "\n    Price: " + ticket.getPrice() + "\n    Discount: " + ticket.getDiscount() +
                "\n    Refundable: " + ticket.getRefundable() +  "\n    Type: " + ticket.getType() + "\n    Person: " +
                getPersonMessage(ticket.getPerson());
    }

    /**
     * Private method for getting user-friendly output of {@link Coordinates}
     * @param coordinates coordinates we want to be outputted
     * @return coordinatesStr coordinates in string format
     */

    private static String getCoordinatesMessage(Coordinates coordinates) {
        return "\n        X: " + coordinates.getX() + "\n        Y: " + coordinates.getY();
    }

    /**
     * Method for getting user-friendly output of {@link Person}
     * @param person person we want to be outputted
     * @return personStr person in string format
     */

    private static String getPersonMessage(Person person) {
        return "\n        Weight: " + person.getWeight() + "\n        Eyes color: " + person.getEyeColor() + "\n        " +
                "Hair color: " + person.getHairColor() + "\n        Nationality: " + person.getNationality();
    }
}
