package server.exceptions;

public class InvalidTicketException extends IllegalArgumentException implements CommonException {

    public InvalidTicketException() {
        super("ERR_INVALID_TICKET");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
