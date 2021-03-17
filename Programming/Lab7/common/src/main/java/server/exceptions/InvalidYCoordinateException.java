package server.exceptions;

public class InvalidYCoordinateException extends InvalidTicketFieldException implements CommonException{
    public InvalidYCoordinateException() {
        super("Invalid y coordinate has been entered. The value should be Integer > -236 and not equal to null.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
