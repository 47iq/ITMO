package server.exceptions;

public class InvalidXCoordinateException extends InvalidTicketFieldException implements CommonException {
    public InvalidXCoordinateException() {
        super("ERR_INVALID_X");
    }
}
