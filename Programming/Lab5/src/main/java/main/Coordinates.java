package main;

public interface Coordinates extends Comparable<Coordinates>{
    double getX();
    Integer getY();
    void setX(double x);
    void setY(Integer y);
}
