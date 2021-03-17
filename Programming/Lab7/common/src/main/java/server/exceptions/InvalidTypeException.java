package server.exceptions;

public class InvalidTypeException extends InvalidTicketFieldException implements CommonException{

    public InvalidTypeException() {
        super("Invalid ticket type has been entered. Check the type list and select a type from it.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
