package common;

import java.io.Serializable;
import java.util.Objects;

public class DefaultCoordinates implements Coordinates, CasterOfDefaultCoordinates, Serializable {

    private static final long serialVersionUID = 12L;

    private double x; //Значение поля должно быть больше -172
    private Integer y; //Значение поля должно быть больше -236, Поле не может быть null

    private DefaultCoordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    protected DefaultCoordinates() {
    }

    public static DefaultCoordinates convert(Coordinates coordinates) {
        return new DefaultCoordinates(coordinates);
    }

    public double getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String toString() {
        return "(" + x + " ," + y + ")";
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.getX(), x) == 0 &&
                y.equals(that.getY());
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setXStr(String x) {
        setX(castXCoordinate(x));
    }

    public void setYStr(String y) {
        setY(castYCoordinate(y));
    }
}
