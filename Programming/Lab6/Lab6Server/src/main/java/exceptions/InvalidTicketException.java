package exceptions;

public class InvalidTicketException extends IllegalArgumentException{
    public InvalidTicketException() {
        super("Invalid manager.ticket has been entered.");
    }
}
