package exceptions;

public class NullTicketException extends IllegalArgumentException{
    public NullTicketException() {
        super("Error got while resolving the ticket.");
    }
}
