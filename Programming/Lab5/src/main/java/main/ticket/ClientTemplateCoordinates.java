package main.ticket;

public class ClientTemplateCoordinates extends DefaultCoordinates {

    /**
     * Constructor for getting {@link Coordinates} from its fields
     * @param x coordinates x
     * @param y coordinates y
     */

    public ClientTemplateCoordinates(double x, Integer y) {
        setX(x);
        setY(y);
    }

    public ClientTemplateCoordinates() {

    }
}
