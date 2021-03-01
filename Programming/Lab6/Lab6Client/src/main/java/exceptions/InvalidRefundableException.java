package exceptions;

public class InvalidRefundableException extends InvalidTicketFieldException{
    public InvalidRefundableException() {
        super("Invalid refundable value has been entered. Refundable must be in {true, false, null}.");
    }
}
