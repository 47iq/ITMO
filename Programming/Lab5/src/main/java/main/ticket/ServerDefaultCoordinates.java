package main.ticket;

import main.CasterOfDefaultCoordinates;

/**
 * Class which is used to store and manage coordinates
 * @autor 47iq
 * @version 1.0
 */

public class ServerDefaultCoordinates extends DefaultCoordinates implements CasterOfDefaultCoordinates {

    /**
     * Constructor for getting {@link Coordinates} from its fields
     * @param x coordinates x
     * @param y coordinates y
     */

    public ServerDefaultCoordinates(double x, Integer y) {
        setX(x);
        setY(y);
    }

    public ServerDefaultCoordinates() {

    }
}