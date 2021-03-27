package server.exceptions;

public class InvalidCountryException extends InvalidTicketFieldException implements CommonException {
    public InvalidCountryException() {
        super("ERR_INVALID_COUNTRY");
    }
}
