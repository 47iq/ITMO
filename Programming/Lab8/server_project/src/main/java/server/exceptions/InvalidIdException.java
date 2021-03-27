package server.exceptions;

public class InvalidIdException extends InvalidTicketFieldException implements ServerException {

    public InvalidIdException() {
        super("ERR_INVALID_ID");
    }
}
