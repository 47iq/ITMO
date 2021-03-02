package exceptions;

public class InvalidTicketException extends IllegalArgumentException{
    public InvalidTicketException() {
        super("Invalid ticket has been entered.");
    }
}
