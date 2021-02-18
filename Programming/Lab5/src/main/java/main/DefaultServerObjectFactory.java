package main;

import main.ticket.*;

/**
 * Class that creates object for the server application
 */

public class DefaultServerObjectFactory implements ServerObjectFactory {

    public ServerTicket convertTicket(Ticket ticket) {
        return new ServerCollectionTicket(ticket);
    }

    public DefaultCoordinates getCoordinates() {
        return new ServerDefaultCoordinates();
    }

    public ServerTicket getServerTicket() {
        return new ServerCollectionTicket();
    }

    public DefaultPerson getPerson() {
        return new ServerDefaultPerson();
    }

    public Coordinates getLeastCoordinates() {
        return new ServerDefaultCoordinates(-171, -235);
    }
}
