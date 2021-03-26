package server.exceptions;

public class InvalidTicketFieldException extends IllegalArgumentException implements CommonException {

    public InvalidTicketFieldException(String message) {
        super(message);
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
