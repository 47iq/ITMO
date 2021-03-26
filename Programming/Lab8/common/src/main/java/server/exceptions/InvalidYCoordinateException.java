package server.exceptions;

public class InvalidYCoordinateException extends InvalidTicketFieldException implements CommonException {
    public InvalidYCoordinateException() {
        super("ERR_INVALID_Y");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
