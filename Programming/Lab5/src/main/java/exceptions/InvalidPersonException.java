package exceptions;

public class InvalidPersonException extends IllegalArgumentException{
    public InvalidPersonException() {
        super("No person has been entered.");
    }
}
