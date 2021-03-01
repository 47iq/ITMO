package exceptions;

public class InvalidCoordinatesException extends InvalidTicketFieldException{
    public InvalidCoordinatesException() {
        super("No coordinates have been entered.");
    }
}
