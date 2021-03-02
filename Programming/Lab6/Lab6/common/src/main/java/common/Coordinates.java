package common;

public interface Coordinates extends Comparable<Coordinates>{
    double getX();
    Integer getY();
    void setX(double x);
    void setY(Integer y);
    default int compareTo(Coordinates o) {
        return (getX() > o.getX() ? 1 : getY() - o.getY());
    }
}
