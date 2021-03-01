package exceptions;

public class InvalidYCoordinateException extends InvalidTicketFieldException{
    public InvalidYCoordinateException() {
        super("Invalid y coordinate has been entered. The value should be Integer > -236 and not equal to null.");
    }
}
