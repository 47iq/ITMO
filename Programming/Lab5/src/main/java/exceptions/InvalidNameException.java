package exceptions;

public class InvalidNameException extends IllegalArgumentException{
    public InvalidNameException() {
        super("Invalid name has been entered. Name can't be an empty string or null");
    }
}
