package server.exceptions;

public class InvalidTicketFieldException extends IllegalArgumentException implements CommonException {

    public InvalidTicketFieldException(String message) {
        super(message);
    }
}
