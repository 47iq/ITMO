package server.exceptions;

public class InvalidRefundableException extends InvalidTicketFieldException implements CommonException {
    public InvalidRefundableException() {
        super("ERR_INVALID_REFUNDABLE");
    }
}
