package server.exceptions;

public class InvalidTicketException extends IllegalArgumentException implements CommonException {

    public InvalidTicketException() {
        super("Invalid ticket has been entered.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
