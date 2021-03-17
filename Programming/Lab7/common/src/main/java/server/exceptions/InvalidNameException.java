package server.exceptions;

public class InvalidNameException extends InvalidTicketFieldException implements CommonException {

    public InvalidNameException() {
        super("Invalid name has been entered. Name can't be an empty string or null.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
