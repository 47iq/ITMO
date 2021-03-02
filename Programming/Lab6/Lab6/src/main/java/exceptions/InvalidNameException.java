package exceptions;

public class InvalidNameException extends InvalidTicketFieldException{
    public InvalidNameException() {
        super("Invalid name has been entered. Name can't be an empty string or null.");
    }
}
