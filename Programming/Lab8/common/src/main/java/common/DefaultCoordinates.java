package common;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class DefaultCoordinates implements Coordinates, Serializable {

    @Serial
    private static final long serialVersionUID = 12L;

    private double x; //Значение поля должно быть больше -172
    private Integer y; //Значение поля должно быть больше -236, Поле не может быть null

    private transient static CoordinatesValidator validator;

    DefaultCoordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    protected DefaultCoordinates() {
    }

    public static DefaultCoordinates convert(Coordinates coordinates) {
        return new DefaultCoordinates(coordinates);
    }

    public static void setValidator(CoordinatesValidator validator) {
        DefaultCoordinates.validator = validator;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + " ," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.getX(), x) == 0 &&
                y.equals(that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public void setXStr(String x) {
        setX(validator.castXCoordinate(x));
    }

    @Override
    public void setYStr(String y) {
        setY(validator.castYCoordinate(y));
    }
}
