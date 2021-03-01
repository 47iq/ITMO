package exceptions;

public class InvalidTicketFieldException extends IllegalArgumentException{
    public InvalidTicketFieldException() {
        super("Illegal manager.ticket field has been entered.");
    }

    public InvalidTicketFieldException(String message) {
        super(message);
    }
}
