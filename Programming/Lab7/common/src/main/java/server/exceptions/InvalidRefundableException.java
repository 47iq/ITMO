package server.exceptions;

public class InvalidRefundableException extends InvalidTicketFieldException implements CommonException {
    public InvalidRefundableException() {
        super("Invalid refundable value has been entered. Refundable must be in {true, false, null}.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.doForRefundable();
    }
}
