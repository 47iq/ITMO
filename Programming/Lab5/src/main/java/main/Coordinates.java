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

public class Coordinates implements Comparable<Coordinates> {
    private double x; //Значение поля должно быть больше -172
    private Integer y; //Значение поля должно быть больше -236, Поле не может быть null

    /**
     * Constructor for getting {@link Coordinates} from its fields
     * @param x {@link Coordinates#x}
     * @param y {@link Coordinates#y}
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

    /**
     * Method which is being used to cast x from String to double
     * @param xCoordinate x String value
     * @return x double value
     */

    private double castXCoordinate (String xCoordinate) {
        double x = Double.parseDouble(xCoordinate);
        if (!xCoordinateValid(x))
            throw new InvalidXCoordinateException();
        else
            return x;
    }

    /**
     * X validator
     * @return true if x is valid, false if not
     */

    public static boolean xCoordinateValid(double x) {
        return x > -172;
    }

    /**
     * Method which is being used to cast y from String to Integer
     * @param yCoordinate y in the String format
     * @return y Integer value
     */

    private Integer castYCoordinate (String yCoordinate) {
        Integer y =  Integer.parseInt(yCoordinate);
        if(!yCoordinateValid(y))
            throw new InvalidYCoordinateException();
        else
            return y;
    }

    /**
     * Y validator
     * @return true if y is valid, false if not
     */

    public static boolean yCoordinateValid(Integer y) {
        return y != null && y > -236;
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