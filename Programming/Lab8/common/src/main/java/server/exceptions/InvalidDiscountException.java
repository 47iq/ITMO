package server.exceptions;

public class InvalidDiscountException extends InvalidTicketFieldException implements CommonException {
    public InvalidDiscountException() {
        super("ERR_INVALID_DISCOUNT");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
