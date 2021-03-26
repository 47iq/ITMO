package server.ticket;

import common.Coordinates;
import common.DefaultCoordinates;

/**
 * Class which is used to store and manage coordinates
 *
 * @version 1.0
 * @autor 47iq
 */

public class ServerDefaultCoordinates extends DefaultCoordinates {

    /**
     * Constructor for getting {@link Coordinates} from its fields
     *
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