package exceptions;

public class InvalidCoordinatesException extends IllegalArgumentException{
    public InvalidCoordinatesException() {
        super("No coordinates have been entered.");
    }
}
