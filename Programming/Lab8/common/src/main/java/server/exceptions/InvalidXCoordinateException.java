package server.exceptions;

public class InvalidXCoordinateException extends InvalidTicketFieldException implements CommonException {
    public InvalidXCoordinateException() {
        super("ERR_INVALID_X");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
