package exceptions;

public class InvalidTypeException extends IllegalArgumentException{
    public InvalidTypeException() {
        super("Invalid ticket type has been entered. Check the type list and select a type from it.");
    }
}
