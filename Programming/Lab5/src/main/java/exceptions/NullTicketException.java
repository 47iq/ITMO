package exceptions;

public class NullTicketException extends IllegalArgumentException{
    public NullTicketException() {
        super("Error got while getting the ticket.");
    }
}
