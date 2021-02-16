package main;

import exceptions.InvalidXCoordinateException;
import exceptions.InvalidYCoordinateException;
import org.json.simple.JSONObject;

import java.util.Objects;

/**
 * Class which is used to store and manage coordinates
 * @autor 47iq
 * @version 1.0
 */

public class Coordinates implements Comparable<Coordinates>, CoordinatesCaster {
    private double x; //Значение поля должно быть больше -172
    private Integer y; //Значение поля должно быть больше -236, Поле не может быть null

    /**
     * Constructor for getting {@link Coordinates} from its fields
     * @param x coordinates x
     * @param y coordinates y
     */

    protected Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates getLeastCoordinates() {
        return new Coordinates(-171, -235);
    }

    /**
     * Constructor used to get main.Coordinates objects from the JSON data
     * @param jsonCoordinates JSONObject of coordinates
     */

    public Coordinates(JSONObject jsonCoordinates){
        x = castXCoordinate((String) jsonCoordinates.get("x"));
        String yStr = (String) jsonCoordinates.get("y");
        if(yStr == null)
            throw new InvalidYCoordinateException();
        else
            y = castYCoordinate(yStr);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public int compareTo(Coordinates myCords) {
        if (myCords.x == x)
            return y - myCords.y;
        else
            return (int) (x - myCords.x);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 &&
                y.equals(that.y);
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Getter for {@link #x}
     * @return {@link #x}
     */

    public double getX() {
        return x;
    }

    /**
     * Getter for {@link #y}
     * @return {@link #y}
     */

    public Integer getY() {
        return y;
    }
}