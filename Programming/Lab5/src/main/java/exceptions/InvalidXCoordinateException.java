package exceptions;

public class InvalidXCoordinateException extends InvalidTicketFieldException{
    public InvalidXCoordinateException() {
        super("Invalid x coordinate has been entered. The value should be double > -172.");
    }
}
