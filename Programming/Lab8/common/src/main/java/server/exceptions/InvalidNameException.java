package server.exceptions;

public class InvalidNameException extends InvalidTicketFieldException implements CommonException {

    public InvalidNameException() {
        super("ERR_INVALID_HAIR");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
