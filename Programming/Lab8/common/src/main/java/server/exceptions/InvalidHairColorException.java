package server.exceptions;

public class InvalidHairColorException extends InvalidTicketFieldException implements CommonException {

    public InvalidHairColorException() {
        super("ERR_INVALID_HAIR");
    }
}
