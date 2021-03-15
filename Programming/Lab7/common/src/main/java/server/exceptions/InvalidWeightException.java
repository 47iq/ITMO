package server.exceptions;

public class InvalidWeightException extends InvalidTicketFieldException implements CommonException {

    public InvalidWeightException() {
        super("Invalid weight value has been entered. Value must be > 0 or null.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.doForWeight();
    }
}
