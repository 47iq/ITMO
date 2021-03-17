package server.exceptions;

public class InvalidDiscountException extends InvalidTicketFieldException implements CommonException{
    public InvalidDiscountException() {
        super("Invalid discount value has been entered. Discount must be double > 0 and <= 100.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
