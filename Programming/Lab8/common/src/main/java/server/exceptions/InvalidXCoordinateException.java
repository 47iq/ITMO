package server.exceptions;

public class InvalidXCoordinateException extends InvalidTicketFieldException implements CommonException{
    public InvalidXCoordinateException() {
        super("Invalid x coordinate has been entered. The value should be double > -172.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
