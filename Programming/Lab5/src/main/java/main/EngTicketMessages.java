package main;

/**
 * Abstract class which manages user-friendly output of {@link Ticket}
 * @autor 47iq
 * @version 1.0
 */

public class EngTicketMessages implements TicketMessenger {

    public EngTicketMessages() {

    }

    /**
     * Method for getting user-friendly output of {@link Ticket}
     * @param ticket ticket we want to be outputted
     * @return ticketStr ticket in string format
     */

    public String getTicketMessage(Ticket ticket) {
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

    private String getCoordinatesMessage(Coordinates coordinates) {
        return "\n        X: " + coordinates.getX() + "\n        Y: " + coordinates.getY();
    }

    /**
     * Method for getting user-friendly output of {@link Person}
     * @param person person we want to be outputted
     * @return personStr person in string format
     */

    private String getPersonMessage(Person person) {
        return "\n        Weight: " + person.getWeight() + "\n        Eyes color: " + person.getEyeColor() + "\n        " +
                "Hair color: " + person.getHairColor() + "\n        Nationality: " + person.getNationality();
    }
}
