package server.exceptions;

public class InvalidTicketException extends IllegalArgumentException implements CommonException {

    public InvalidTicketException() {
        super("ERR_INVALID_TICKET");
    }
}
