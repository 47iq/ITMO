package server.exceptions;

public class InvalidTypeException extends InvalidTicketFieldException implements CommonException {

    public InvalidTypeException() {
        super("ERR_INVALID_TYPE");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
