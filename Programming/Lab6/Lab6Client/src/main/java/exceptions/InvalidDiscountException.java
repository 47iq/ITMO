package exceptions;

public class InvalidDiscountException extends InvalidTicketFieldException{
    public InvalidDiscountException() {
        super("Invalid discount value has been entered. Discount must be double > 0 and <= 100.");
    }
}
