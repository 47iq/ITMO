package main;

import exceptions.InvalidXCoordinateException;
import exceptions.InvalidYCoordinateException;

import java.io.IOException;

public interface CoordinatesCaster {
    /**
     * Method which is being used to cast x from String to double
     * @param xCoordinate x String value
     * @return x double value
     */

    default double castXCoordinate (String xCoordinate) {
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

    default boolean xCoordinateValid(double x) {
        return x > -172;
    }

    /**
     * Method which is being used to cast y from String to Integer
     * @param yCoordinate y in the String format
     * @return y Integer value
     */

    default Integer castYCoordinate (String yCoordinate) {
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

    default boolean yCoordinateValid(Integer y) {
        return y != null && y > -236;
    }
}
