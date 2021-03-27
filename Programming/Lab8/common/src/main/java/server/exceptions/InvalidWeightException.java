package server.exceptions;

public class InvalidWeightException extends InvalidTicketFieldException implements CommonException {

    public InvalidWeightException() {
        super("ERR_INVALID_WEIGHT");
    }
}
