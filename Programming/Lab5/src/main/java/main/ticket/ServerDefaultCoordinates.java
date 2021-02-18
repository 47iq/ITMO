package main.ticket;

import exceptions.InvalidYCoordinateException;
import main.CasterOfDefaultCoordinates;
import org.json.simple.JSONObject;

/**
 * Class which is used to store and manage coordinates
 * @autor 47iq
 * @version 1.0
 */

public class ServerDefaultCoordinates extends AbstractCoordinates implements CasterOfDefaultCoordinates {

    /**
     * Constructor for getting {@link Coordinates} from its fields
     * @param x coordinates x
     * @param y coordinates y
     */

    public ServerDefaultCoordinates(double x, Integer y) {
        setX(x);
        setY(y);
    }

    /**
     * Constructor used to get manager.ticket.Coordinates objects from the JSON data
     * @param jsonCoordinates JSONObject of coordinates
     */

    public ServerDefaultCoordinates(JSONObject jsonCoordinates){
        setX(castXCoordinate((String) jsonCoordinates.get("x")));
        String yStr = (String) jsonCoordinates.get("y");
        if(yStr == null)
            throw new InvalidYCoordinateException();
        else
            setY(castYCoordinate(yStr));
    }
}