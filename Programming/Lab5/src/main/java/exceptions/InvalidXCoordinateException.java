package exceptions;

public class InvalidXCoordinateException extends IllegalArgumentException{
    public InvalidXCoordinateException() {
        super("Invalid x coordinate has been entered. The value should be double > -172");
    }
}
