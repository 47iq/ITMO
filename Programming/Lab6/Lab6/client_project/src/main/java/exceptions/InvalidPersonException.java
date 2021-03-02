package exceptions;

public class InvalidPersonException extends InvalidTicketFieldException{
    public InvalidPersonException() {
        super("No person has been entered.");
    }
}
