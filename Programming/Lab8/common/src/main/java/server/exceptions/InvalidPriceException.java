package server.exceptions;

public class InvalidPriceException extends InvalidTicketFieldException implements CommonException {

    public InvalidPriceException() {
        super("ERR_INVALID_PRICE");
    }
}
